package net.darksky.example.ui.common

import android.graphics.Color
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import net.darksky.example.AppExecutors
import net.darksky.example.R
import net.darksky.example.databinding.DataDetailItemBinding
import net.darksky.example.vo.DataDetail

/**
 * A RecyclerView adapter for [DataDetail] class.
 */
class DataItemListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors
) : DataBoundListAdapter<DataDetail, DataDetailItemBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<DataDetail>() {
            override fun areItemsTheSame(oldItem: DataDetail, newItem: DataDetail): Boolean {
                return oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: DataDetail, newItem: DataDetail): Boolean {
                return return oldItem.time == newItem.time
                        && oldItem.temperatureLow == newItem.temperatureLow
                        && oldItem.temperature == newItem.temperature
                        && oldItem.temperatureHigh == newItem.temperatureHigh
                        && oldItem.humidity == newItem.humidity
                        && oldItem.windSpeed == newItem.windSpeed
                        && oldItem.precipProbability == newItem.precipProbability
            }
        }
) {

    override fun createBinding(parent: ViewGroup): DataDetailItemBinding {
        val binding = DataBindingUtil.inflate<DataDetailItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.data_detail_item,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.data?.let {
                notifyDataSetChanged()
            }
        }
        return binding
    }

    override fun bind(binding: DataDetailItemBinding, item: DataDetail) {
        binding.data = item
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<DataDetailItemBinding>, position: Int) {
        if(position % 2 == 0) {
            holder.binding.root.setBackgroundResource(R.color.colorPrimary)
        }else{
            holder.binding.root.setBackgroundResource(R.color.lightBlue)
        }
        super.onBindViewHolder(holder, position)
    }
}
