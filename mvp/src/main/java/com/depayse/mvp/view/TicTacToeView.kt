package com.depayse.mvp.view

interface TicTacToeView {
    fun showWinner(winningPlayerDisplayLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(rowIdx: Int, colIdx: Int, text: String)
}