package com.example.trainlivelocation.utli

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Device
import com.example.shoshaplaystation.databinding.DevicesRcvItemLayoutBinding
import com.example.shoshaplaystation.util.BindableAdapter

class DeviceCustomAdapter(private val postListener: DeviceListener) :
    RecyclerView.Adapter<DeviceAdapterViewHolder>(), BindableAdapter<ArrayList<Device>> {

    private var binding: DevicesRcvItemLayoutBinding? = null
    lateinit var postArrayList: ArrayList<Device>
    var postList = emptyList<Device>()

    public fun updateData(postList: ArrayList<Device>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapterViewHolder {
        binding = DevicesRcvItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DeviceAdapterViewHolder(binding!!, postListener)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: DeviceAdapterViewHolder, position: Int) {
        val post = postList.get(position)
        holder.bind(post)
    }

    override fun setData(data: ArrayList<Device>) {
        this.postList = data
        notifyDataSetChanged()
    }
}
