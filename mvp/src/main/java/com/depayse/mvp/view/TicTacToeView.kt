package com.depayse.mvp.view

/**
 * View 가 해야할 기능이 interface 로 정의되면서 View 가 해야하는 역할이 분리되어 명세되며, 이를 통해 View 가 어떤 기능을 하는지 한 눈에 볼 수 있다.
 *
 * [TicTacToeView] interface 는 MVP 패턴에서 View 에 해당한다.
 * ```
 * ```
 * Functions View has to do are defined as an interface so functions are separated and can be seen at a glance.
 *
 * [TicTacToeView] interface is responsible for View in MVP Pattern.
 */
interface TicTacToeView {
    fun showWinner(winningPlayerDisplayLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(rowIdx: Int, colIdx: Int, text: String)
}