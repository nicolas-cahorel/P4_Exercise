package com.openclassrooms.notes.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.databinding.NoteBinding
import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.utils.NoteDiffCallback
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * An adapter for displaying a list of notes in a RecyclerView.
 * @param notesFlow The flow of notes to display.
 */
class NoteAdapter(
    private val notesFlow: Flow<List<Note>>,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<NoteViewHolder>() {

    private var notes: List<Note> = emptyList()

    init {
        observeNotes()
    }

    /**
     * Observes the flow of notes and updates the adapter when new notes are emitted.
     */
    private fun observeNotes() {
        lifecycleOwner.lifecycleScope.launch {notesFlow.collect { newNotes ->
                val diffResult = calculateDiff(notes, newNotes)
                notes = newNotes
                diffResult.dispatchUpdatesTo(this@NoteAdapter)
            }
        }
    }

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    private fun calculateDiff(oldNotes: List<Note>, newNotes: List<Note>): DiffUtil.DiffResult {
        val diffCallback = NoteDiffCallback(oldNotes, newNotes)
        return DiffUtil.calculateDiff(diffCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }
}
