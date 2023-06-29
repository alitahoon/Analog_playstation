package com.example.shoshaplaystation.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import javax.inject.Inject

class DeleteDeviceDialogPresenter @Inject constructor(

){
    private var view: DeleteDeviceDialogView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "HomePresenter"
    fun attachView(view: DeleteDeviceDialogView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}