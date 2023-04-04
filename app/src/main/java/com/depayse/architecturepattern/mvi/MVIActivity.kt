package com.depayse.architecturepattern.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.depayse.architecturepattern.R

class MVIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvi_activity)
    }
}