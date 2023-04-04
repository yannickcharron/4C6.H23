package ca.qc.cstj.s09navigationdrawer.ui.planet.detail

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentPlanetBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlanetFragment : Fragment(R.layout.fragment_planet) {

    private val args: PlanetFragmentArgs by navArgs()

    private val binding: FragmentPlanetBinding by viewBinding()
    private val viewModel: PlanetViewModel by viewModels {
        PlanetViewModel.Factory(args.href)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.planetUiState.onEach {
            when(it) {
                PlanetUiState.Empty -> Unit
                is PlanetUiState.Error -> {
                    Toast.makeText(requireContext(), it.exception.localizedMessage, Toast.LENGTH_LONG).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is PlanetUiState.Success -> {
                    binding.txvPlanetName.text = it.planet.name
                    binding.txvDetailDiscoveredBy.text = it.planet.discoveredBy

                    Glide.with(this)
                        .load(it.planet.image)
                        .into(binding.imvDetailPlanet)

                    (requireActivity() as AppCompatActivity).supportActionBar?.title = it.planet.name
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


}