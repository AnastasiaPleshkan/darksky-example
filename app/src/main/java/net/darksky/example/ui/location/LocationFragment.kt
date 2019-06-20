package net.darksky.example.ui.location

import android.Manifest
import android.annotation.SuppressLint
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.darksky.example.AppExecutors
import net.darksky.example.binding.FragmentDataBindingComponent
import net.darksky.example.databinding.LocationFragmentBinding
import net.darksky.example.di.Injectable
import net.darksky.example.util.autoCleared
import javax.inject.Inject
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import androidx.core.app.ActivityCompat
import net.darksky.example.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import android.widget.Toast
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import io.reactivex.disposables.CompositeDisposable

class LocationFragment : Fragment(), Injectable {

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<LocationFragmentBinding>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.location_fragment,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        checkPlayServicesAvailable()
    }

    private fun checkPlayServicesAvailable() {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val status = apiAvailability.isGooglePlayServicesAvailable(requireActivity())

        if (status != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(status)) {
                apiAvailability.getErrorDialog(requireActivity(), status, 1).show()
            } else {
                Snackbar.make(requireView(), "Google Play Services unavailable. This app will not work", Snackbar.LENGTH_INDEFINITE).show()
            }
        } else {
            val result = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            if (result != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1)
            } else {
                request()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun request() {
        val rxLocation = RxLocation(requireActivity())
        val locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(5000)
        val disposable = CompositeDisposable()
        disposable.add(
                rxLocation.location().updates(locationRequest)
                        .flatMap { location -> rxLocation.geocoding().fromLocation(location).toObservable() }
                        .subscribe { it ->
                            disposable.clear()
                            navController().navigate(LocationFragmentDirections.show(it.latitude.toString(), it.longitude.toString()))
                        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                request()
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(requireActivity(), "Permission denied to get your Location", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
