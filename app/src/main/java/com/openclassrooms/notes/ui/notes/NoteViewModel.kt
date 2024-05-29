package com.openclassrooms.notes.ui.notes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the list of notes and interacting with the repository.
 * This class observes changes to the list of notes in the repository and provides them to the UI
 * through LiveData. It also contains logic for adding new notes.
 *
 * @param noteRepository The repository responsible for providing access to note data.
 */
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    /**
     * MutableSharedFlow object containing the list of notes.
     * This is private to prevent direct modification from outside the ViewModel.
     */
    private val _notes = MutableSharedFlow<List<Note>>()

    /**
     * SharedFlow object that provides read-only access to the list of notes.
     * This is public to allow the UI to observe changes to the list of notes.
     */
    val notes = _notes.asSharedFlow()

    /**
     * Initializes the ViewModel by collecting notes from the repository.
     */
    init {
        collectNotes()
    }

    /**
     * Collects the list of notes from the repository and updates the LiveData object.
     */
    fun collectNotes() {
        viewModelScope.launch {
            noteRepository.notes.collect { noteList ->
                _notes.emit(noteList)
            }
        }
    }

    /**
     * Function to add a new note.
     */
    fun addNewNote() {
        // Logic to add a new note
    }

}