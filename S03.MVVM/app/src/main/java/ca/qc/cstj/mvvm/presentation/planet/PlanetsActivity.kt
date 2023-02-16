package ca.qc.cstj.mvvm.presentation.planet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.mvvm.R
import ca.qc.cstj.mvvm.databinding.ActivityPlanetsBinding
import ca.qc.cstj.mvvm.presentation.planet.adapters.PlanetRecyclerViewAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlanetsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPlanetsBinding
    private val viewModel : PlanetsViewModel by viewModels()

    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter(listOf())

        //binding.rcvPlanets.layoutManager = LinearLayoutManager(this)
        binding.rcvPlanets.layoutManager = GridLayoutManager(this,2)
        binding.rcvPlanets.adapter = planetRecyclerViewAdapter

        //newState = le plus récent kayak (state) sur la rivière (flow)
        //planetUiState = la rivière
        viewModel.planetsUiState.onEach { newState ->
            when(newState) {
                PlanetsUiState.Empty -> Unit
                is PlanetsUiState.Success -> {
                    planetRecyclerViewAdapter.planets = newState.planets
                    planetRecyclerViewAdapter.notifyDataSetChanged()
                }
            }
        }.launchIn(lifecycleScope) //Démarre le Thread



    }


    //La partie statique (static) de la classe
    companion object {
       fun newIntent(context: Context) : Intent {
           return Intent(context, PlanetsActivity::class.java)
       }
    }
}