package ca.qc.cstj.s09navigationdrawer.ui.other

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentFunBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FunFragment : Fragment(R.layout.fragment_fun) {

    private val viewModel : FunViewModel by viewModels()
    private val binding : FragmentFunBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTimer.setOnClickListener {
            viewModel.startTimer()
            binding.btnTimer.visibility = View.INVISIBLE
        }

        viewModel.funUiState.onEach {
            when(it){
                FunUiState.Empty -> Unit
                FunUiState.Finished -> {
                    binding.txvCounter.text = getString(R.string.completed)
                    binding.btnTimer.visibility = View.VISIBLE
                }
                is FunUiState.Working -> {
                    binding.txvCounter.text = it.progression.toString()
                    binding.pgbLoading.setProgress(it.progression, true)
                }
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }
}