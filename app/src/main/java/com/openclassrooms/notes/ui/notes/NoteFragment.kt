package com.openclassrooms.notes.ui.notes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.FragmentNoteBinding
import com.openclassrooms.notes.ui.recyclerview.NoteAdapter
import com.openclassrooms.notes.ui.recyclerview.NoteItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment for displaying notes.
 *
 * This fragment is responsible for displaying a list of notes using a RecyclerView. It observes
 * the NoteViewModel to get the latest notes and updates the RecyclerView accordingly. It also
 * initializes a Floating Action Button (FAB) for adding new notes.
 */
class NoteFragment : Fragment() {

    private lateinit var noteAdapter: NoteAdapter

    /**
     * The binding for the fragment layout.
     */
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModel()

    /**
     * Inflates the fragment layout and returns the root view.
     *
     * @param inflater The LayoutInflater used to inflate the layout.
     * @param container The parent view that this fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The root view of the fragment's layout.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called immediately after `onCreateView()` has returned, but before any saved state has been restored into the view.
     *
     * @param view The View returned by `onCreateView()`.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializes the adapter
        noteAdapter = NoteAdapter()

        initRecyclerView()
        initFABButton()
        observeNotes()
    }

    /**
     * Called when the view previously created by `onCreateView()` has been detached from the fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Observes the list of notes in the ViewModel and updates the RecyclerView adapter accordingly.
     */
    private fun observeNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            noteViewModel.notes.collectLatest { noteAdapter.updateNotes(it) }
        }
        noteViewModel.collectNotes()
    }

    /**
     * Initializes the Floating Action Button (FAB).
     *
     * The FAB shows a dialog when clicked, indicating that adding new notes is not yet available.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }

    /**
     * Initializes the RecyclerView.
     *
     * Sets up the RecyclerView with an item decoration and assigns the NoteAdapter to it.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )
            // Set the initialized adapter to the RecyclerView to display notes
            adapter = noteAdapter
        }
    }
}