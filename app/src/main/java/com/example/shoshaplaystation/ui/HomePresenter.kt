package com.example.shoshaplaystation.ui

import android.view.View

class HomePresenter {
    private var view: View? = null

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }
}