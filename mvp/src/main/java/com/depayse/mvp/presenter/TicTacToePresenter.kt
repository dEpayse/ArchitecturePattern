package com.depayse.mvp.presenter

import com.depayse.mvc.model.Board
import com.depayse.mvp.view.TicTacToeView

class TicTacToePresenter(private val view: TicTacToeView): Presenter {
    private val model: Board = Board()

    override fun onCreate() {}
    override fun onPause() {}
    override fun onResume() {}
    override fun onDestroy() {}

    fun onButtonSelected(rowIdx: Int, colIdx: Int) {
        model.mark(rowIdx, colIdx)?.let {
            view.setButtonText(rowIdx, colIdx, it.toString())
            model.winner?.let {
                view.showWinner(it.toString())
            }
        }
    }

    fun onResetSelected() {
        view.clearWinnerDisplay()
        view.clearButtons()
        model.restart()
    }
}