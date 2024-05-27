package com.openclassrooms.notes.data.repository

import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 */
class NoteRepository {

    /**
     * The API service for interacting with notes.
     */
    private val notesApiService: NotesApiService = LocalNotesApiService()

    /**
     * A flow that emits a list of all notes.
     */
    val notes: Flow<List<Note>> = flow {
        emit(notesApiService.getAllNotes())
    }
}