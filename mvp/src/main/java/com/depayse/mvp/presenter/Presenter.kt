package com.depayse.mvp.presenter

interface Presenter {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}