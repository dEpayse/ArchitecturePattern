package com.depayse.mvp.presenter

interface BasePresenter {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}