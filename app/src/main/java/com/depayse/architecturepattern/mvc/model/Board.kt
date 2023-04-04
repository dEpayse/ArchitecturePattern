package com.depayse.architecturepattern.mvc.model

class Board {
    private val boardSize = 3
    private val cells = Array(boardSize) { Array(boardSize) { Cell() } }

    private var winner : Player? = null
    private var state = GameState.IN_PROGRESS
    private var currentTurnPlayer = Player.X


    private enum class GameState { IN_PROGRESS, FINISHED }

    fun restart() {
        clearCells()
        winner = null
        currentTurnPlayer = Player.X
        state = GameState.IN_PROGRESS
    }

    fun mark(rowIdx: Int, colIdx: Int) {
        if(isValid(rowIdx, colIdx)) {
            cells[rowIdx][colIdx].value = currentTurnPlayer

            if(isWinningMoveByPlayer(currentTurnPlayer, rowIdx, colIdx)) {
                state = GameState.FINISHED
                winner = currentTurnPlayer
                return
            }

            flipCurrentTurn()
        }
    }

    private fun clearCells() {
        cells.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                cells[rowIdx][colIdx].value = null
            }
        }
    }

    private fun isValid(rowIdx: Int, colIdx: Int) : Boolean {
        if(state == GameState.FINISHED) return false
        if(isOutOfBounds(rowIdx) || isOutOfBounds(colIdx)) return false
        if(isCellValueAlreadySet(rowIdx, colIdx)) return false
        return true
    }

    private fun isOutOfBounds(idx: Int) = idx < 0 || idx >= boardSize
    private fun isCellValueAlreadySet(rowIdx: Int, colIdx: Int) : Boolean = cells[rowIdx][colIdx].value != null

    private fun isWinningMoveByPlayer(player: Player, currentRowIdx: Int, currentColIdx: Int) : Boolean {
        val isRowBingo = cells[currentRowIdx].all { it.value == player }
        val isColumnBingo = cells.all { it[currentColIdx].value == player }
        val isDiagonalBingo = currentRowIdx == currentColIdx && (0 until boardSize).all { cells[it][it].value == player}
        val isOppositeDiagonalBingo = currentRowIdx + currentColIdx == boardSize - 1 && (0 until boardSize).all { cells[it][boardSize - 1 - it].value == player }
        return isRowBingo || isColumnBingo || isDiagonalBingo || isOppositeDiagonalBingo
    }

    private fun flipCurrentTurn() {
        currentTurnPlayer =
            if(currentTurnPlayer == Player.X) Player.O
            else Player.X
    }

}