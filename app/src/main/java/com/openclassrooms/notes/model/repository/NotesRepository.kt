package com.openclassrooms.notes.model.repository

import com.openclassrooms.notes.model.data.Note
import com.openclassrooms.notes.model.service.LocalNotesApiService
import com.openclassrooms.notes.model.service.NotesApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 */
class NotesRepository {

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