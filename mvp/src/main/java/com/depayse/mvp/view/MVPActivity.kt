package com.depayse.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.core.view.children
import androidx.core.view.get
import com.depayse.mvp.R
import com.depayse.mvp.databinding.ActivityMvpBinding
import com.depayse.mvp.presenter.TicTacToePresenter

class MVPActivity : AppCompatActivity(), TicTacToeView {

    private val presenter = TicTacToePresenter(this)
    private lateinit var viewBinding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setOnCellClickListener()

        presenter.onCreate()
    }

    private fun setOnCellClickListener() {
        viewBinding.tableBoard.children.forEach { tableRowView ->
            (tableRowView as? TableRow)?.children?.forEach { buttonView ->
                buttonView.setOnClickListener { onCellClicked(buttonView) }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_reset -> {
                presenter.onResetSelected()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    override fun showWinner(winningPlayerDisplayLabel: String) {
        viewBinding.winnerPlayerLabel.text = winningPlayerDisplayLabel
        viewBinding.winnerPlayerViewGroup.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        viewBinding.winnerPlayerViewGroup.visibility = View.GONE
        viewBinding.winnerPlayerLabel.text = ""
    }

    override fun clearButtons() {
        viewBinding.tableBoard.children.forEach { tableRowView ->
            (tableRowView as? TableRow)?.children?.forEach { buttonView ->
                (buttonView as? Button)?.text = ""
            }
        }
    }

    private fun onCellClicked(v: View) {
        (v as? Button)?.let { _ ->
            val tableRow = viewBinding.tableBoard.children.find { (it as? TableRow)?.children?.contains(v) == true } as? TableRow
            val rowIdx = viewBinding.tableBoard.indexOfChild(tableRow)
            val colIdx = tableRow?.indexOfChild(v) ?: -1

            presenter.onButtonSelected(rowIdx, colIdx)
        }
    }

    override fun setButtonText(rowIdx: Int, colIdx: Int, text: String) {
        (viewBinding.tableBoard.children.toList()[rowIdx] as? TableRow)?.let {
            (it[colIdx] as? Button)?.text = text
        }
    }
}