package com.example.trainlivelocation.utli

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.DevicesRcvItemLayoutBinding
import com.example.shoshaplaystation.util.BindableAdapter

class DeviceCustomAdapter(private val deviceListener: DeviceListener) :
    RecyclerView.Adapter<DeviceAdapterViewHolder>(), BindableAdapter<ArrayList<DeviceEntity>> {
    private var selectedPosition = RecyclerView.NO_POSITION
    private var binding: DevicesRcvItemLayoutBinding? = null
    lateinit var devicesArrayList: ArrayList<DeviceEntity>
    var devicestList = emptyList<DeviceEntity>()

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
        notifyItemInserted(position)
    }
    fun remove(position: Int) {
        devicesArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun setData(data: ArrayList<DeviceEntity>) {
        this.devicesArrayList = data
    }
//    private fun animateItemBackground(position: Int) {
//        if (selectedPosition == position) {
//            // Item is already selected, no need to animate
//            return
//        }
//
//        val previousPosition = selectedPosition
//        selectedPosition = position
//
//        // Reset the background color of the previously selected item
//        if (previousPosition != RecyclerView.NO_POSITION) {
//            notifyItemChanged(previousPosition)
//        }
//
//        recyclerView.getChildAt(position)?.let { itemView ->
//            val startColor = itemView.backgroundTintList?.defaultColor ?: 0
//            val endColor = Color.parseColor("#00DFA2") // Desired end color
//
//            val animator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
//            animator.duration = 3000 // Animation duration in milliseconds
//
//            animator.addUpdateListener { valueAnimator ->
//                val color = valueAnimator.animatedValue as Int
//                itemView.setBackgroundColor(color)
//            }
//
//            animator.start()
//
//            animator.addListener(object : Animator.AnimatorListener {
//                override fun onAnimationStart(p0: Animator) {}
//
//                override fun onAnimationEnd(p0: Animator) {
//                    deviceListener.onDeviceLongClicked(postList.get(position))
//                }
//
//                override fun onAnimationCancel(p0: Animator) {}
//
//                override fun onAnimationRepeat(p0: Animator) {}
//            })
//        }
//    }

}
