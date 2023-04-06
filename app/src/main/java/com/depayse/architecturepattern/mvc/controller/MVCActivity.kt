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

/**
 * Android 에서 MVC 를 구현할 때 Activity 가 Controller 가 된다.
 *
 * [MVCActivity]는 MVC 패턴에서 Controller 역할을 한다.
 *  ```
 *  ```
 * In android, Activity becomes Controller when implementing MVC Pattern.<br/><br/>
 *
 * [MVCActivity] is responsible for Controller in MVC Pattern.<br/><br/>
 */
class MVCActivity : AppCompatActivity() {

    /**
     * Controller 는 Model 과 View 에 대해 알고 있다.
     *  ```
     *  ```
     *  Controller does know about Model and View.
     */
    private lateinit var model: Board
    private lateinit var viewBinding: ActivityMvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setOnCellClickListener()

        model = Board()
    }

    /**
     * Controller 는 입력을 받는 역할을 한다.
     *
     * [setOnCellClickListener] 는 Controller 가 보드의 셀 하나를 클릭하는 입력을 받도록 해준다.
     * ```
     * ```
     * Controller takes user input.
     *
     * [setOnCellClickListener] makes Controller take input of a single cell of a board.
     */
    private fun setOnCellClickListener() {
        viewBinding.tableBoard.children.forEach { tableRowView ->
            (tableRowView as? TableRow)?.children?.forEach { buttonView ->
                buttonView.setOnClickListener { onCellClicked(buttonView) }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tictactoe, menu)
        return true
    }

    /**
     * Controller 는 입력을 받는 역할을 한다.
     *
     * [onOptionsItemSelected] 는 Controller 가 초기화 옵션을 클릭하는 입력을 받도록 해준다.
     * ```
     * ```
     * Controller takes user input.
     *
     * [onOptionsItemSelected] makes Controller take input of a reset option.
     */
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

    /**
     * Controller 는 사용자의 입력을 받고, Model 과 View 의 변경을 모니터링하고, 업데이트한다.
     *
     * [onCellClicked] 는 사용자가 셀을 클릭하는 입력이 일어난 후에 Model 과 View 를 업데이트한다.
     * ```
     * ```
     * Controller takes user input, monitors changes of Model and View, updates Model and View.
     *
     * [onCellClicked] updates Model and View after user clicks a single cell.
     */
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

    /**
     * Controller 는 사용자의 입력을 받고, Model 과 View 의 변경을 모니터링하고, 업데이트한다.
     *
     * [reset] 은 사용자가 reset 옵션을 클릭하는 입력이 일어난 후에 Model 과 View 를 초기화한다.
     * ```
     * ```
     * Controller takes user input, monitors changes of Model and View, updates Model and View.
     *
     * [reset] function resets Model and View after user clicks reset option.
     */
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