package ca.qc.cstj.s09navigationdrawer.ui.trader

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentTraderBinding

class TraderFragment : Fragment(R.layout.fragment_trader) {

    private val binding: FragmentTraderBinding by viewBinding()
    private val viewModel: TraderViewModel by viewModels()


}