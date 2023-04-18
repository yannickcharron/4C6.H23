package ca.qc.cstj.s09navigationdrawer.ui.barcode

import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn

sealed class BarcodeUiState {
    class Error(val exception: Exception) : BarcodeUiState()
    class Success(val checkIn: CheckIn) : BarcodeUiState()
    object Empty: BarcodeUiState()
}