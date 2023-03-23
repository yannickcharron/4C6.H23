package ca.qc.cstj.meteomania.ui.search

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.meteomania.R
import ca.qc.cstj.meteomania.core.Constants
import ca.qc.cstj.meteomania.core.text
import ca.qc.cstj.meteomania.databinding.FragmentSearchBinding
import ca.qc.cstj.meteomania.domain.models.Meteo
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

//IMPORTANT : il faut passer le layout au constructeur de la classe mère Fragment
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel : SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Notre code pour le fragment
        viewModel.searchUiState.onEach {
            when(it) {
                SearchUiState.Empty -> Unit
                is SearchUiState.Error -> {
                    binding.txvNotAvailable.visibility = View.VISIBLE
                    binding.grpMeteo.visibility = View.INVISIBLE
                    binding.pgbLoading.hide()
                    Toast.makeText(requireContext(),it.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
                SearchUiState.Loading -> {
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                    binding.grpMeteo.visibility = View.INVISIBLE
                    binding.pgbLoading.show()
                }
                is SearchUiState.Success -> {
                    binding.txvNotAvailable.visibility = View.INVISIBLE
                    binding.grpMeteo.visibility = View.VISIBLE
                    binding.pgbLoading.hide()

                    displayMeteo(it.meteo)
                }
            }

        }.launchIn(lifecycleScope)

        binding.btnSearch.setOnClickListener {
            viewModel.search(binding.tilSearch.text)
        }
    }

    private fun displayMeteo(meteo: Meteo) {

        binding.tilSearch.text = meteo.city
        binding.txvCity.text = meteo.city
        binding.txvTemperature.text = getString(R.string.temperatureFormat, meteo.temperature)
        binding.txvSky.text = meteo.weather

        //Image du drapeau du pays
        Glide.with(requireContext())
            .load(Constants.COUNTRY_IMAGE_API.format(meteo.country.lowercase()))
            .into(binding.imvCountry)

        //Date
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

            binding.txvDateAPI.text = formatter.format(
                Instant.ofEpochSecond(meteo.timestamp.toLong()).atZone(ZoneOffset.UTC).plusSeconds(meteo.timezone.toLong()))
            binding.txvDatePhone.text = formatter.format(Instant.now().atZone(ZoneId.systemDefault()))
        }

        //Background

        val background = if(meteo.temperature > Constants.TEMPERATURE_LOWER_BOUND) {
            ContextCompat.getDrawable(requireContext(), R.drawable.warm)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.cold)
        }

        val ctlMainActivity = requireActivity().findViewById<ConstraintLayout>(R.id.container)
        background?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(requireContext(), R.color.filter), PorterDuff.Mode.DARKEN)

        ctlMainActivity.background = background

    }


}