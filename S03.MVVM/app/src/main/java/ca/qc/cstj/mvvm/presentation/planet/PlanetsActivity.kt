package ca.qc.cstj.mvvm.presentation.planet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ca.qc.cstj.mvvm.R
import ca.qc.cstj.mvvm.databinding.ActivityPlanetsBinding

class PlanetsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlanetsBinding
    private val viewModel : PlanetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    //La partie statique (static) de la classe
    companion object {
       fun newIntent(context: Context) : Intent {
           return Intent(context, PlanetsActivity::class.java)
       }
    }
}