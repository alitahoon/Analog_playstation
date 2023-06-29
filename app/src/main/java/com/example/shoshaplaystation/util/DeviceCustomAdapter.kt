package com.example.trainlivelocation.utli

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.DevicesRcvItemLayoutBinding
import com.example.shoshaplaystation.util.BindableAdapter

class DeviceCustomAdapter(private val deviceListener: DeviceListener) :
    RecyclerView.Adapter<DeviceAdapterViewHolder>(), BindableAdapter<ArrayList<DeviceEntity>> {

    private var binding: DevicesRcvItemLayoutBinding? = null
    lateinit var postArrayList: ArrayList<DeviceEntity>
    var postList = emptyList<DeviceEntity>()

    public fun updateData(postList: ArrayList<DeviceEntity>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapterViewHolder {
        binding = DevicesRcvItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeviceAdapterViewHolder(binding!!, deviceListener)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: DeviceAdapterViewHolder, position: Int) {
        val device = postList.get(position) as DeviceEntity
        holder.bind(device)
        holder.itemView.setOnLongClickListener {
            deviceListener.onDeviceLongClicked(device)
        }
    }

    override fun setData(data: ArrayList<DeviceEntity>) {
        this.postList = data
        notifyDataSetChanged()
    }
}
