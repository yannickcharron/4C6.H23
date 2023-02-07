package ca.qc.cstj.mvvm.presentation.main

import androidx.lifecycle.ViewModel
import ca.qc.cstj.mvvm.domain.models.Pilot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val pilot = Pilot("YellowKiwi", 15)

    private val _pilotUiState = MutableStateFlow(PilotUiState(true, pilot))
    val pilotUiState = _pilotUiState.asStateFlow()


    fun fly(revolution: Int, isTraining: Boolean) {
        if(pilot.canFly()) {
            pilot.fly(revolution, isTraining)
            _pilotUiState.value = PilotUiState(true, pilot)
        } else {
            _pilotUiState.value = PilotUiState(false, pilot)
        }
    }

    fun recharge() {
        pilot.recharge()
        _pilotUiState.value = PilotUiState(true, pilot)
    }

}