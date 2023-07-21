package com.example.trainlivelocation.utli

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.DevicesRcvItemLayoutBinding
import com.example.shoshaplaystation.util.BindableAdapter

class DeviceCustomAdapter(private val deviceListener: DeviceListener) :
    RecyclerView.Adapter<DeviceAdapterViewHolder>(), BindableAdapter<ArrayList<DeviceEntity>> {
    private var selectedPosition = RecyclerView.NO_POSITION
    private var binding: DevicesRcvItemLayoutBinding? = null
    lateinit var devicesArrayList: ArrayList<DeviceEntity>
    var devicestList = emptyList<DeviceEntity>()
    private  var isDiffultItem:Boolean=false

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

    override fun getItemCount(): Int = devicesArrayList.size

    override fun onBindViewHolder(holder: DeviceAdapterViewHolder, position: Int) {
        val device = devicesArrayList.get(position)
        holder.bind(device)
        holder.itemView.setOnLongClickListener {
         deviceListener.onDeviceLongClicked(device)
        }

    }
    fun remove(position: Int) {
        devicesArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun setData(data: ArrayList<DeviceEntity>) {
        this.devicesArrayList = data
//        isDiffultItem = data.size==1

    }

}
