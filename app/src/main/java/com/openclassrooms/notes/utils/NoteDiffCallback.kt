package com.openclassrooms.notes.utils

import androidx.recyclerview.widget.DiffUtil
import com.openclassrooms.notes.data.model.Note

/**
 * Callback for calculating the difference between two lists of notes.
 * Used by RecyclerView's adapter to efficiently update the list of notes.
 *
 * @param oldList The old list of notes.
 * @param newList The new list of notes.
 */
class NoteDiffCallback(
    private val oldList: List<Note>,
    private val newList: List<Note>
) : DiffUtil.Callback() {

    /**
     * Returns the size of the old list.
     */
    override fun getOldListSize(): Int = oldList.size

    /**
     * Returns the size of the new list.
     */
    override fun getNewListSize(): Int = newList.size

    /**
     * Checks whether the items at the given positions in the old and new lists are the same.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    /**
     * Checks whether the contents of the items at the given positions in the old and new lists are the same.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}