package ca.qc.cstj.remotedatasource.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.remotedatasource.databinding.ActivityMainBinding
import ca.qc.cstj.remotedatasource.domain.models.Planet
import ca.qc.cstj.remotedatasource.presentation.adapters.PlanetRecyclerViewAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter()

        val linearLayoutManager = LinearLayoutManager(this)

        binding.rcvPlanets.layoutManager = linearLayoutManager
        binding.rcvPlanets.adapter = planetRecyclerViewAdapter

        viewModel.mainUiState.onEach {
            when(it) {
                MainUiState.Empty -> Unit
                is MainUiState.Error -> Unit
                is MainUiState.Success -> {
                    planetRecyclerViewAdapter.planets = it.planets
                    planetRecyclerViewAdapter.notifyDataSetChanged()
                }
                MainUiState.Loading -> Unit
            }
        }.launchIn(lifecycleScope)

    }

}