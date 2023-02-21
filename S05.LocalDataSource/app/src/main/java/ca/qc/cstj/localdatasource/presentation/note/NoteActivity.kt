package ca.qc.cstj.localdatasource.presentation.note

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.localdatasource.R
import com.example.localdatasource.databinding.ActivityNoteBinding
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private val viewModel : NoteViewModel by viewModels()

    private var _color = "#a1887f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabColor.setOnClickListener {
            //TODO: Dialog pour le choix de la couleur de la note

        }

        binding.fabSave.setOnClickListener {
            //TODO : Sauvegarder la note

        }
    }

    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, NoteActivity::class.java)
        }
    }
}