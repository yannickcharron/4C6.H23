package ca.qc.cstj.s09navigationdrawer.ui.barcode

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.s09navigationdrawer.R
import ca.qc.cstj.s09navigationdrawer.databinding.FragmentBarcodeBinding
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class BarcodeFragment : Fragment(R.layout.fragment_barcode) {

    private val binding : FragmentBarcodeBinding by viewBinding()
    private val  viewModel: BarcodeViewModel by viewModels()

    private val scanQRCode = registerForActivityResult(ScanQRCode(), ::handleQuickieResult)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQuickie.setOnClickListener {
            scanQRCode.launch(null)
        }

        viewModel.barcodeUiState.onEach {
            when(it) {
                BarcodeUiState.Empty -> Unit
                is BarcodeUiState.Error -> Toast.makeText(requireContext(), it.exception.localizedMessage, Toast.LENGTH_LONG).show()
                is BarcodeUiState.Success -> Toast.makeText(requireContext(), it.checkIn.toString(), Toast.LENGTH_LONG).show()
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleQuickieResult(qrResult: QRResult) {

        when(qrResult) {
            is QRResult.QRSuccess -> {
                binding.txvCodeContent.text = qrResult.content.rawValue
                viewModel.addCheckIn(qrResult.content.rawValue)
            }
            QRResult.QRUserCanceled -> binding.txvCodeContent.text = getString(R.string.user_canceled)
            QRResult.QRMissingPermission -> binding.txvCodeContent.text = getString(R.string.missing_permission)
            is QRResult.QRError -> binding.txvCodeContent.text = qrResult.exception.localizedMessage
        }
    }

}