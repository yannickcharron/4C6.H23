package ca.qc.cstj.s09navigationdrawer.ui.other

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FunViewModel : ViewModel() {

    private val _funUiState = MutableStateFlow<FunUiState>(FunUiState.Empty)
    val funUiState = _funUiState.asStateFlow()

    private var _timerCounter = 0


    private val timer = object: CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timerCounter++
            _funUiState.update {
                FunUiState.Working(_timerCounter)
            }
        }

        override fun onFinish() {
            cancel()
            _funUiState.update {
                FunUiState.Finished
            }
        }
    }

    fun startTimer() {
        _timerCounter = 0
        timer.start()
    }
}