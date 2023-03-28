package ca.qc.cstj.s09navigationdrawer.ui.element

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentElementsBinding

class ElementsFragment : Fragment(R.layout.fragment_elements) {

    private val binding: FragmentElementsBinding by viewBinding()
    private val viewModel: ElementsViewModel by viewModels()

}