package com.depayse.architecturepattern.mvc.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.core.view.children
import com.depayse.architecturepattern.R
import com.depayse.architecturepattern.databinding.ActivityMvcBinding
import com.depayse.architecturepattern.mvc.model.Board

class MVCActivity : AppCompatActivity() {

    private lateinit var model: Board
    private lateinit var viewBinding: ActivityMvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        model = Board()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_reset -> {
                reset()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun onCellClicked(v: View) {
        (v as? Button)?.let { button ->
            val tableRow = viewBinding.tableBoard.children.find { (it as? TableRow)?.children?.contains(v) == true } as? TableRow
            val rowIdx = viewBinding.tableBoard.indexOfChild(tableRow)
            val colIdx = tableRow?.indexOfChild(v) ?: -1

            model.mark(rowIdx, colIdx)?.let { playerWhoMoved ->
                button.text = playerWhoMoved.toString()
                model.winner?.let {
                    viewBinding.winnerPlayerLabel.text = playerWhoMoved.toString()
                    viewBinding.winnerPlayerViewGroup.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun reset() {
        viewBinding.winnerPlayerViewGroup.visibility = View.GONE
        viewBinding.winnerPlayerLabel.text = ""

        model.restart()

        viewBinding.tableBoard.children.forEach { tableRowView ->
            (tableRowView as? TableRow)?.children?.forEach { buttonView ->
                (buttonView as? Button)?.text = ""
            }
        }
    }
}