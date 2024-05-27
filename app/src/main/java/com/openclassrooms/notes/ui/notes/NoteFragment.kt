package com.openclassrooms.notes.ui.notes



import com.openclassrooms.notes.ui.recyclerview.NoteAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.R
import com.openclassrooms.notes.data.repository.NoteRepository
import com.openclassrooms.notes.databinding.FragmentNoteBinding
import com.openclassrooms.notes.ui.recyclerview.NoteItemDecoration

/**
 * A fragment for displaying notes.
 */
class NoteFragment : Fragment() {

    // Déclaration du repository et de l'adaptateur
    private val noteRepository = NoteRepository()
    private lateinit var noteAdapter : NoteAdapter

    /**
     * The binding for the fragment layout.
     */
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation de l'adaptateur
        noteAdapter = NoteAdapter(noteRepository.notes, viewLifecycleOwner)

        initRecyclerView()
        initFABButton()
        observeNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Observes the list of notes in the ViewModel and updates the RecyclerView adapter accordingly.
     */
    private fun observeNotes() {
        noteViewModel.notes.observe(viewLifecycleOwner) { notes ->
            noteAdapter.updateNotes(notes)
        }
    }

    /**
     * Initializes the FAB button.
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
