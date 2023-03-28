package ca.qc.cstj.s09navigationdrawer.ui.planet

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentPlanetBinding

class PlanetFragment : Fragment(R.layout.fragment_planet) {

    private val binding: FragmentPlanetBinding by viewBinding()
    private val viewModel: PlanetViewModel by viewModels()




}