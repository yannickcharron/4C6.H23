package ca.qc.cstj.mvvm.presentation.main

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.mvvm.R
import ca.qc.cstj.mvvm.core.Constants
import ca.qc.cstj.mvvm.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var rocketAnimation: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate crée les objets des contrôle (Button, ImageView, TextView, ...)
        // ATTENTION -> il faut changer le paramètre de l'appel de setContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //À chaque fois qu'un nouveau state est reçu
        viewModel.mainUiState.onEach {
            when(it) {
                MainUiState.Empty -> Unit
                is MainUiState.Error -> {
                    Snackbar.make(binding.root, R.string.msg_recharge, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.recharge) {
                            viewModel.recharge()
                        }.show()
                }
                MainUiState.Loading -> {
                    binding.btnStart.isEnabled = false
                    rocketAnimation.start()
                }
                is MainUiState.Success -> {
                    with(binding) {
                        txvPilotName.text = it.pilot.name
                        txvLevel.text = getString(R.string.level, it.pilot.level)
                        txvLife.text = it.pilot.life.toString()
                        txvShield.text = it.pilot.shield.toString()
                        txvEnergy.text = it.pilot.energy.toString()
                        txvCube.text = it.pilot.cube.toString()
                        btnStart.isEnabled = true
                    }

                }
            }
        }.launchIn(lifecycleScope)

        binding.btnStart.setOnClickListener {
            val revolution = binding.sldRevolution.value.toInt()
            val isTraining = binding.swtTraining.isChecked

            createAnimation()

            viewModel.fly(revolution, isTraining)
        }

    }


    private fun createAnimation() {

        val layoutParams = binding.imvRocket.layoutParams as ConstraintLayout.LayoutParams
        val repeatCount = binding.sldRevolution.value.toInt()
        val startAngle = layoutParams.circleAngle
        val endAngle = startAngle - 360

        rocketAnimation = ValueAnimator.ofFloat(startAngle, endAngle)
        rocketAnimation.repeatCount = repeatCount - 1
        rocketAnimation.duration = Constants.REVOLUTION_DURATION
        rocketAnimation.interpolator = AnticipateOvershootInterpolator()

        rocketAnimation.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            layoutParams.circleAngle = animatedValue
            binding.imvRocket.layoutParams = layoutParams
            binding.imvRocket.rotation = animatedValue - 90
        }




    }
}