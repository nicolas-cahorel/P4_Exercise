package com.openclassrooms.notes.data.service

import com.openclassrooms.notes.data.model.Note

/**
 * Defines the contract for the API that manages the notes.
 *
 * This interface provides methods for adding notes and retrieving all notes.
 */
interface NotesApiService {

    /**
     * Adds a note.
     *
     * @param note The note to add.
     */
    fun addNote(note: Note)

    /**
     * Returns all the notes.
     *
     * @return The list of notes.
     */
    fun getAllNotes(): List<Note>

}