package ca.qc.cstj.s09navigationdrawer.ui.planet.list

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentListPlanetsBinding
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlanetListFragment : Fragment(R.layout.fragment_list_planets) {

    private val binding: FragmentListPlanetsBinding by viewBinding()
    private val viewModel: PlanetListViewModel by viewModels()

    private lateinit var planetRecyclerViewAdapter: PlanetRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planetRecyclerViewAdapter = PlanetRecyclerViewAdapter(listOf(), ::onRecyclerViewPlanetClick)

        binding.rcvPlanets.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = planetRecyclerViewAdapter
        }

//        binding.swlRefresh.setOnRefreshListener {
//            viewModel.refreshPlanets()
//            binding.swlRefresh.isRefreshing = false
//        }

        viewModel.mainUiState.onEach {
            when(it) {
                is PlanetListUiState.Error -> {
                    Toast.makeText(requireContext(), it.exception?.localizedMessage ?: getString(R.string.apiErrorMessage), Toast.LENGTH_SHORT).show()
                }
                is PlanetListUiState.Success -> {
                    binding.rcvPlanets.visibility = View.VISIBLE

                    planetRecyclerViewAdapter.planets = it.planets
                    planetRecyclerViewAdapter.notifyDataSetChanged()
                    binding.pgbLoading.hide()

                }
                PlanetListUiState.Loading ->  {
                    binding.rcvPlanets.visibility = View.GONE
                    binding.pgbLoading.show()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onRecyclerViewPlanetClick(planet: Planet) {
        val action = PlanetListFragmentDirections.actionNavListPlanetToPlanetFragment(planet.href)
        findNavController().navigate(action)
    }


}