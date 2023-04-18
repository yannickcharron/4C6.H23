package ca.qc.cstj.s09navigationdrawer.ui.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.ApiResult
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.data.repositories.CheckInRepository
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BarcodeViewModel : ViewModel() {

    private val checkInRepository = CheckInRepository()

    private val _barcodeUiState = MutableStateFlow<BarcodeUiState>(BarcodeUiState.Empty)
    val barcodeUiState = _barcodeUiState.asStateFlow()

    fun addCheckIn(rawValue: String) {
        viewModelScope.launch {
            val checkIn = CheckIn(rawValue, Constants.DOOR)
            checkInRepository.create(checkIn).collect { apiResult ->
                _barcodeUiState.update {
                    when(apiResult) {
                        is ApiResult.Error -> BarcodeUiState.Error(apiResult.throwable as Exception)
                        ApiResult.Loading -> BarcodeUiState.Empty
                        is ApiResult.Success -> BarcodeUiState.Success(apiResult.data)
                    }
                }
            }
        }

    }
}