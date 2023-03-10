package ca.qc.cstj.meteomania.ui.notifications

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.meteomania.R
import ca.qc.cstj.meteomania.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val binding : FragmentNotificationsBinding by viewBinding()
    private val viewModel: NotificationsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Notre code pour le fragment
    }
}