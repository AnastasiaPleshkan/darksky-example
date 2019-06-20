package net.darksky.example.ui.forecast

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch
import belka.us.androidtoggleswitch.widgets.ToggleSwitch
import net.darksky.example.AppExecutors
import net.darksky.example.R
import net.darksky.example.binding.FragmentDataBindingComponent
import net.darksky.example.databinding.ForecastFragmentBinding
import net.darksky.example.di.Injectable
import net.darksky.example.ui.common.ClickCallback
import net.darksky.example.ui.common.DataItemListAdapter
import net.darksky.example.usecases.DefaultObserver
import net.darksky.example.util.autoCleared
import net.darksky.example.vo.Forecast
import net.darksky.example.vo.Resource
import net.darksky.example.vo.Status
import timber.log.Timber
import javax.inject.Inject

class ForecastFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<ForecastFragmentBinding>()

    lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.forecast_fragment,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ForecastViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        load()
        binding.btnSwitch.setOnToggleSwitchChangeListener { position: Int, isChecked: Boolean ->
            when (position) {
                0 -> {
                    binding.currently = binding.resource?.data?.currently
                    (binding.detail.adapter as DataItemListAdapter).submitList(binding.resource?.data?.hourly?.data)
                }
                1 -> {
                    binding.currently = binding.resource?.data?.daily?.data?.get(0)
                    (binding.detail.adapter as DataItemListAdapter)
                            .submitList(binding.resource?.data?.daily?.data?.size?.let { binding.resource?.data?.daily?.data?.subList(1, it) })
                }
            }
        }
        binding.detail.adapter = DataItemListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
        )

        binding.callback = object : ClickCallback {
            override fun click() {
                load()
            }
        }
    }

    private fun load() {
        binding.resource = Resource(Status.LOADING,null, getString(R.string.loading))

        val lat = ForecastFragmentArgs.fromBundle(arguments!!).lat.toDouble()
        val lon = ForecastFragmentArgs.fromBundle(arguments!!).lon.toDouble()
        viewModel.load(lat, lon, ForecastObserver())
    }

    inner class ForecastObserver : DefaultObserver<Forecast>() {
        override fun onComplete() {
        }

        override fun onNext(t: Forecast) {
            binding.resource = Resource(Status.SUCCESS, t, null)
            binding.currently = t.currently
            (binding.detail.adapter as DataItemListAdapter).submitList(t.hourly?.data)
        }

        override fun onError(exception: Throwable) {
            binding.resource = Resource(Status.ERROR, null, exception.message)
        }
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
