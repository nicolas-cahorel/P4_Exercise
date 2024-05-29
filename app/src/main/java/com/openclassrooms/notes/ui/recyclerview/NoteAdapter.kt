package com.openclassrooms.notes.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.databinding.NoteBinding
import com.openclassrooms.notes.utils.NoteDiffCallback

/**
 * An adapter for displaying a list of notes in a RecyclerView.
 * This adapter utilizes a DiffUtil to efficiently update the list of notes.
 */
class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var notes: List<Note> = emptyList()

    /**
     * Updates the list of notes displayed by the adapter.
     * @param newNotes The new list of notes to display.
     */
    fun updateNotes(newNotes: List<Note>) {
        val diffResult = calculateDiff(notes, newNotes)
        notes = newNotes
        diffResult.dispatchUpdatesTo(this@NoteAdapter)
    }

    /**
     * Calculates the difference between the old list of notes and the new list of notes
     * using a DiffUtil.DiffResult.
     * @param oldNotes The old list of notes.
     * @param newNotes The new list of notes.
     * @return The DiffUtil.DiffResult representing the differences between the old and new lists.
     */
    private fun calculateDiff(oldNotes: List<Note>, newNotes: List<Note>): DiffUtil.DiffResult {
        val diffCallback = NoteDiffCallback(oldNotes, newNotes)
        return DiffUtil.calculateDiff(diffCallback)
    }

    /**
     * Creates a new NoteViewHolder by inflating the layout for a note item.
     * @param parent The parent ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new NoteViewHolder that holds the inflated note item layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    /**
     * Gets the number of notes in the adapter.
     * @return The number of notes in the adapter.
     */
    override fun getItemCount(): Int = notes.size

    /**
     * Binds the note at the specified position to the given holder.
     * @param holder The NoteViewHolder to bind the note to.
     * @param position The position of the note in the list.
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }
}
