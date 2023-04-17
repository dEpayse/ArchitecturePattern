package com.depayse.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.depayse.mvvm.model.Board

class TicTacToeViewModel : ViewModel {
    private val model = Board()

    private val _cellsText = MutableLiveData(Array(3) { Array(3) { "" } })
    val cellsText: LiveData<Array<Array<String>>> = _cellsText

    private val _winnerText = MutableLiveData<String>()
    val winnerText: LiveData<String> = _winnerText

    override fun onCreate() {}
    override fun onPause() {}
    override fun onResume() {}
    override fun onDestroy() {}

    fun onResetSelected() {
        model.restart()
        _winnerText.value = null
        _cellsText.value?.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                _cellsText.value?.get(rowIdx)?.set(colIdx, "")
            }
        }
        _cellsText.value = _cellsText.value
    }

    fun onClickedCellAt(rowIdx: Int, colIdx: Int) {
        val playerThatMoved = model.mark(rowIdx, colIdx)
        _cellsText.value?.get(rowIdx)?.set(colIdx, playerThatMoved?.toString() ?: "")
        _cellsText.value = _cellsText.value
        _winnerText.value = model.winner?.toString()
    }
}