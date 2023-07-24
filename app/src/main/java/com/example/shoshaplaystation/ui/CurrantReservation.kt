package com.example.shoshaplaystation.ui

import Resource
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.databinding.FragmentCurrantReservationBinding
import com.example.shoshaplaystation.util.PlaystationReservationCustomAdapter
import com.example.shoshaplaystation.util.PlaystationReservationListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrantReservation : Fragment() ,PlaystationReservationListener,CurrantReservationView{
    private val TAG="CurrantReservation"
    private var binding:FragmentCurrantReservationBinding?=null


    @Inject
    lateinit var currantReservationPresenter:CurrantReservationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCurrantReservationBinding.inflate(inflater,container,false)
            .apply {

            }
        currantReservationPresenter.attachView(this)
        currantReservationPresenter!!.getReservations()

        return binding!!.root
    }

    companion object {

    }

    override fun onDeviceClicked(reservation: PlaystationReservationEntity) {

    }

    override fun onDeviceLongClicked(reservation: PlaystationReservationEntity): Boolean {
        TODO("Not yet implemented")
    }

    override fun onGettingReservations(result: Resource<ArrayList<PlaystationReservationEntity>>) {
        when(result){
            is Resource.Loading->{
                Log.i(TAG,"getting reservations")
            }
            is Resource.Failure->{
                Log.e(TAG,"${result.error}")
            }
            is Resource.Success->{
                Log.e(TAG,"${result.data}")
                val adapter=PlaystationReservationCustomAdapter(this)
                adapter.setData(result.data)
                binding!!.currantReservationRCV.adapter=adapter
            }
            else -> {}
        }
    }
}