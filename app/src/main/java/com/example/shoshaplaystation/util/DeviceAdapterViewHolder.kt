package com.example.trainlivelocation.utli
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.DevicesRcvItemLayoutBinding

class DeviceAdapterViewHolder(
    private val binding:DevicesRcvItemLayoutBinding,
    private val deviceListener: DeviceListener
) :RecyclerView.ViewHolder(binding.root){
    private val TAG="DeviceAdapterViewHolder"
    fun bind(device: DeviceEntity) {
        binding.device = device
        binding.listener=deviceListener
//        if (deviceListSize==currantDeviceIndex){
//            val drawable = ContextCompat.getDrawable(binding.deviceItemImage.context, R.drawable.baseline_add_24)
//            binding.deviceItemImage.setImageDrawable(drawable)
//            binding.listSize=deviceListSize
//        }

    }
}