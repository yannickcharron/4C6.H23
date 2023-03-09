package ca.qc.cstj.meteomania.ui.search

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.qc.cstj.meteomania.R
import ca.qc.cstj.meteomania.databinding.FragmentSearchBinding

//IMPORTANT : il faut passer le layout au constructeur de la classe mère Fragment
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel : SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Notre code pour le fragment
    }


}