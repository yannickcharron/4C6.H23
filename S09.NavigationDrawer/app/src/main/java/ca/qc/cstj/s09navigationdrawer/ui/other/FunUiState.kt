package ca.qc.cstj.s09navigationdrawer.ui.other

sealed class FunUiState {
    object Empty : FunUiState()
    class Working(val progression: Int): FunUiState()
    object Finished: FunUiState()
}