package com.example.shoshaplaystation.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.databinding.ReservationRcvLtemLayoutBinding


class PlaystationReservationCustomAdapter(private val playstationReservationListener: PlaystationReservationListener) :
    RecyclerView.Adapter<PlaystationReservationViewHolder>(), BindableAdapter<ArrayList<PlaystationReservationEntity>> {
    private var selectedPosition = RecyclerView.NO_POSITION
    private var binding: ReservationRcvLtemLayoutBinding? = null
    lateinit var devicesArrayList: ArrayList<PlaystationReservationEntity>
    var devicestList = emptyList<PlaystationReservationEntity>()
    private  var isDiffultItem:Boolean=false

    public fun updateData(postList: ArrayList<PlaystationReservationEntity>) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaystationReservationViewHolder {
        binding = ReservationRcvLtemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaystationReservationViewHolder(binding!!, playstationReservationListener)
    }

    override fun getItemCount(): Int = devicesArrayList.size

    override fun onBindViewHolder(holder: PlaystationReservationViewHolder, position: Int) {
        val reservation = devicesArrayList.get(position)
        holder.bind(reservation)
        holder.itemView.setOnLongClickListener {
            playstationReservationListener.onDeviceLongClicked(reservation)
        }

    }
    fun remove(position: Int) {
        devicesArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun setData(data: ArrayList<PlaystationReservationEntity>) {
        this.devicesArrayList = data
//        isDiffultItem = data.size==1

    }

}