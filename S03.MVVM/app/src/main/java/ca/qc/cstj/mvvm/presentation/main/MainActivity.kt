package ca.qc.cstj.mvvm.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.mvvm.R
import ca.qc.cstj.mvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate crée les objets des contrôle (Button, ImageView, TextView, ...)
        // ATTENTION -> il faut changer le paramètre de l'appel de setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //À chaque fois qu'un nouveau state est reçu
        viewModel.pilotUiState.onEach {

            when(it) {
                PilotUiState.Empty -> Unit
                is PilotUiState.Error -> {

                }

                PilotUiState.Loading -> {

                }
                is PilotUiState.Success -> {

                }
            }

//            if(it.isSuccess) {
//                with(binding) {
//                    txvPilotName.text = it.pilot.name
//                    txvLevel.text = getString(R.string.level, it.pilot.level)
//                    txvLife.text = it.pilot.life.toString()
//                    txvShield.text = it.pilot.shield.toString()
//                    txvEnergy.text = it.pilot.energy.toString()
//                    txvCube.text = it.pilot.cube.toString()
//                }
//            } else  {
//                Snackbar.make(binding.root, R.string.msg_recharge, Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.recharge) {
//                        viewModel.recharge()
//                    }.show()
//            }


        }.launchIn(lifecycleScope)

        binding.btnStart.setOnClickListener {
            val revolution = binding.sldRevolution.value.toInt()
            val isTraining = binding.swtTraining.isChecked

            viewModel.fly(revolution, isTraining)
        }

    }
}