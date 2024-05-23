package com.openclassrooms.notes.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.view.recyclerview.NoteItemDecoration
import com.openclassrooms.notes.view.recyclerview.NoteAdapter
import com.openclassrooms.notes.viewmodel.NoteViewModel

/**
 * The main activity for the app.
 */
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    private lateinit var noteViewModel: NoteViewModel

    private val noteAdapter = NoteAdapter(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        initRecyclerView()
        initFABButton()
        observeNotes()
    }

    /**
     * Observes the list of notes in the ViewModel and updates the RecyclerView adapter accordingly.
     */
    private fun observeNotes() {
        noteViewModel.notes.observe(this) {
            notes -> noteAdapter.updateNotes(notes)
        }
    }

    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }

    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = noteAdapter
        }

    }

}
