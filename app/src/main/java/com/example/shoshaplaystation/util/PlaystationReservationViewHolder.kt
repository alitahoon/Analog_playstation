package com.example.shoshaplaystation.util

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.databinding.ReservationRcvLtemLayoutBinding

class PlaystationReservationViewHolder(
    private val binding: ReservationRcvLtemLayoutBinding,
    private val listener:PlaystationReservationListener?
) : RecyclerView.ViewHolder(binding.root){
    private val TAG="DeviceAdapterViewHolder"
    fun bind(reservation: PlaystationReservationEntity) {
        binding.reservation = reservation
    }
}