package com.example.shoshaplaystation.ui

import Resource
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.FragmentHomeBinding
import com.example.shoshaplaystation.util.DeviceCountChangeListener
import com.example.trainlivelocation.utli.DeviceCustomAdapter
import com.example.trainlivelocation.utli.DeviceListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Home : Fragment() ,HomeView,DeviceListener,DeviceCountChangeListener,DeleteDeviceDialogView{
    private var binding:FragmentHomeBinding?=null
    private val TAG="Home"

    @Inject
    lateinit var  homePresenter:HomePresenter

    private var adapter:DeviceCustomAdapter= DeviceCustomAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePresenter.attachView(this)
        homePresenter.getDevices()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
            .apply {

            }
        binding!!.homeAddDevciesButton.setOnClickListener{
            val bottomSheetDialogFragment = AddDevicesDialog(this)
            bottomSheetDialogFragment.show(childFragmentManager, "AddDevicesDialog")

        }
        return binding!!.root
    }

    companion object {

    }

    override fun getDevicesFromDatabase(result: Resource<ArrayList<DeviceEntity>>) {
        when (result){
            is Resource.Success->{
                Log.i(TAG,"${result.data}")
                adapter.setData(result.data)
                binding!!.homeRcvDevices.adapter=adapter
            }
            is Resource.Failure->{
                Log.e(TAG,"${result.error}")
            }
            is Resource.Loading->{
                Log.i(TAG,"getting devices")

            }

            else -> {}
        }
    }

    override fun onDeviceClicked(device: DeviceEntity) {
    }

    override fun onDeviceLongClicked(device: DeviceEntity): Boolean {
        val bottomSheetDialogFragment = DeleteDeviceDialog(this)
        bottomSheetDialogFragment.show(childFragmentManager, "DeleteDeviceDialog")
        return true
    }

    override fun onDeviceAdded() {
        homePresenter.getDevices()
    }

    override fun onDeviceDeleted() {
        homePresenter.getDevices()
    }

    override fun onDeviceDeleted(result: Resource<String>) {
        when (result){
            is Resource.Success->{
                Log.i(TAG,"${result.data}")
            }
            is Resource.Failure->{
                Log.e(TAG,"${result.error}")
            }
            is Resource.Loading->{
                Log.i(TAG,"deleting devices")

            }
            else -> {}
        }    }
}