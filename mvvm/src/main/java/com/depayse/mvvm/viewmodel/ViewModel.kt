package com.depayse.mvvm.viewmodel

interface ViewModel {
    fun onCreate()
    fun onPause()
    fun onResume()
    fun onDestroy()
}