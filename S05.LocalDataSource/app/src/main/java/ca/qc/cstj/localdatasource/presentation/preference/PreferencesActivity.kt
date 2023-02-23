package ca.qc.cstj.localdatasource.presentation.preference

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.localdatasource.presentation.note.NoteActivity
import ca.qc.cstj.localdatasource.presentation.note.NoteViewModel
import com.example.localdatasource.R
import com.example.localdatasource.databinding.ActivityNoteBinding
import com.example.localdatasource.databinding.ActivityPreferencesBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding
    private val viewModel : PreferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.preferencesUiState.onEach {
            when(it) {
                PreferencesUiState.Empty -> Unit
                is PreferencesUiState.Success -> {
                    binding.edtName.setText(it.userPreferences.name)
                    binding.swtDarkMode.isChecked = it.userPreferences.isDarkMode
                }
            }
        }.launchIn(lifecycleScope)

        binding.btnSavePreferences.setOnClickListener {
            val name = binding.edtName.text.toString()
            viewModel.saveName(name)
        }

        binding.swtDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveDarkMode(isChecked)
        }


        binding.btnClosePreferences.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, PreferencesActivity::class.java)
        }
    }
}