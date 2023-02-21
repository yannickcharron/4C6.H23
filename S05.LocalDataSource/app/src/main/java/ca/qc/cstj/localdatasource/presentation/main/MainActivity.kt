package ca.qc.cstj.localdatasource.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.localdatasource.databinding.ActivityMainBinding
import ca.qc.cstj.localdatasource.presentation.main.adapters.NoteRecyclerViewAdapter
import ca.qc.cstj.localdatasource.presentation.note.NoteActivity
import ca.qc.cstj.localdatasource.presentation.preference.PreferencesActivity

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    private val noteRecyclerViewAdapter = NoteRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: Configurer le layoutManager (grid de 2 colonnes) et l'adapter du recyclerView

        // TODO : Traiter le changement d'état


        binding.fabAddNote.setOnClickListener {
            startActivity(NoteActivity.newIntent(this))
        }

        binding.fabSettings.setOnClickListener {
            startActivity(PreferencesActivity.newIntent(this))
        }

    }

}