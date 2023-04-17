package com.depayse.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.depayse.mvvm.R
import com.depayse.mvvm.databinding.ActivityMvvmBinding
import com.depayse.mvvm.viewmodel.TicTacToeViewModel

class MVVMActivity : AppCompatActivity() {

    val viewModel = TicTacToeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMvvmBinding>(this, R.layout.activity_mvvm).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        viewModel.onCreate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}