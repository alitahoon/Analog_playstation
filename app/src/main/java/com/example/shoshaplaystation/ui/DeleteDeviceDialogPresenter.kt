package com.example.shoshaplaystation.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class DeleteDeviceDialogPresenter {
    private var view: HomeView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "HomePresenter"
    fun attachView(view: HomeView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}