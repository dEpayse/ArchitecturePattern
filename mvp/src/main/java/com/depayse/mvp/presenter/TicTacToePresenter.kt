package com.depayse.mvp.presenter

import com.depayse.mvc.model.Board
import com.depayse.mvp.view.TicTacToeView

/**
 * Presenter 는 Model 과 View 를 업데이트하고, View 와 1 : 1 관계이다.
 *
 * Presenter 는 Model 과 View 를 연결하는 역할을 한다.
 *
 * Presenter 는 MVC 의 Controller 와는 다르게 View 와 interface 를 통해 연결된다.
 *
 * [TicTacToePresenter] 는 MVP Pattern 에서 Presenter 역할을 한다.
 * ```
 * ```
 * Presenter updates Model and View, has a 1 : 1 relationship with View.
 *
 * Presenter connects Model and View.
 *
 * Presenter is connected to View through interface, which is different from Controller of MVC.
 *
 * [TicTacToePresenter] is responsible for Presenter in MVP Pattern.
 */
class TicTacToePresenter(private val view: TicTacToeView): BasePresenter {
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