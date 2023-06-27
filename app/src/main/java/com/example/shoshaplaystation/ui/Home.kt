package com.example.shoshaplaystation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() ,HomeContracts{
    private var binding:FragmentHomeBinding?=null
    private val homePresenter:HomePresenter=HomePresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
            .apply {

            }


        return binding!!.root
    }

    companion object {

    }
}