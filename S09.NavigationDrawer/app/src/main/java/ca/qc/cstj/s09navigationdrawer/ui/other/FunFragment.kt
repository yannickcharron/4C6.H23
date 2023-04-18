package ca.qc.cstj.s09navigationdrawer.ui.other

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentFunBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FunFragment : Fragment(R.layout.fragment_fun) {

    private lateinit var mediaPlayer: MediaPlayer

    private val viewModel : FunViewModel by viewModels()
    private val binding : FragmentFunBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.success)

        binding.btnTimer.setOnClickListener {
            viewModel.startTimer()
            binding.btnTimer.visibility = View.INVISIBLE
            binding.vdvVideo.start()
        }

        try {
            binding.vdvVideo.setVideoPath(Constants.VIDEO_URL)
            binding.vdvVideo.start()
        } catch (ex: Exception) {
            Toast.makeText(requireContext(), ex.message, Toast.LENGTH_LONG).show()
        }


        viewModel.funUiState.onEach {
            when(it){
                FunUiState.Empty -> Unit
                FunUiState.Finished -> {
                    binding.txvCounter.text = getString(R.string.completed)
                    binding.btnTimer.visibility = View.VISIBLE
                    mediaPlayer.start()
                }
                is FunUiState.Working -> {
                    binding.txvCounter.text = it.progression.toString()
                    binding.pgbLoading.setProgress(it.progression, true)
                }
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }
}