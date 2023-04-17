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

/**
 * Android 에서 MVP 를 적용할 때, Activity 가 View 인터페이스를 구현하고 Activity 는 View 가 된다.
 *
 * [MVPActivity]는 MVP 패턴에서 View 역할을 한다.
 *  ```
 *  ```
 * In android, Activity implements View interface and it becomes View when implementing MVP Pattern.
 *
 * [MVPActivity] is responsible for View in MVP Pattern.
 */
class MVPActivity : AppCompatActivity(), TicTacToeView {

    /**
     * View 는 Presenter 에 대해 알고 있고, 사용자에게 보여지는 UI 를 그린다.
     *  ```
     *  ```
     *  View does know about Presenter and draws UI seen by user.
     */
    private val presenter = TicTacToePresenter(this)
    private lateinit var viewBinding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setOnCellClickListener()

        presenter.onCreate()
    }

    /**
     * View 는 입력을 받는 역할을 한다.
     *
     * [setOnCellClickListener] 는 View 가 보드의 셀 하나를 클릭하는 입력을 받도록 해준다.
     * ```
     * ```
     * View takes user input.
     *
     * [setOnCellClickListener] makes View take input of a single cell of a board.
     */
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

    /**
     * View 는 입력을 받는 역할을 한다.
     *
     * [onOptionsItemSelected] 는 View 가 초기화 옵션을 클릭하는 입력을 받도록 해준다.
     * ```
     * ```
     * View takes user input.
     *
     * [onOptionsItemSelected] makes View take input of a reset option.
     */
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

    /**
     * View 는 사용자의 입력을 받고, Presenter 에게 입력에 맞는 행위를 명령한다.
     *
     * [onCellClicked] 는 사용자가 셀을 클릭하는 입력이 일어난 후에 Presenter 에게 해당 입력에 맞는 행위를 명령한다.
     * ```
     * ```
     * View takes user input, commands Presenter to execute proper code block.
     *
     * [onCellClicked] command Presenter to execute proper code block after user clicks a single cell.
     */
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