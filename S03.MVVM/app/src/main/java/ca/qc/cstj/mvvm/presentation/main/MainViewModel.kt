package ca.qc.cstj.mvvm.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.mvvm.core.Constants
import ca.qc.cstj.mvvm.domain.models.Pilot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val pilot = Pilot("YellowKiwi", 15)

    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val mainUiState = _mainUiState.asStateFlow()

    init {
        _mainUiState.update {
            //return@update MainUiState.Success(pilot)
            MainUiState.Success(pilot)
        }
    }

    fun fly(revolution: Int, isTraining: Boolean) {
        if(pilot.canFly()) {
            viewModelScope.launch {
                //1. Changement d'état pour Loading (Animating)
                _mainUiState.update {
                    MainUiState.Loading
                }

                //2. Attendre x milliseconds
                delay(Constants.REVOLUTION_DURATION * revolution)

                //3. Voler
                pilot.fly(revolution, isTraining)

                //4. Changement d'état pour Success
                _mainUiState.update {
                    //il pourrait avoir return@update
                    MainUiState.Success(pilot)
                }
            }
        } else {
            _mainUiState.update {
                //il pourrait avoir return@update
                MainUiState.Error("Can't Fly")
            }
        }
    }

    fun recharge() {
        pilot.recharge()
        _mainUiState.value = MainUiState.Success(pilot)
    }

}