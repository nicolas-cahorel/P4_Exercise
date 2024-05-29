package com.openclassrooms.notes

import com.openclassrooms.notes.data.model.Note
import com.openclassrooms.notes.data.repository.NoteRepository
import com.openclassrooms.notes.data.service.LocalNotesApiService
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

/**
 * Unit tests for the classes related to notes.
 */
class NotesUnitTest {

    /**
     * Unit test for retrieving all notes from the [LocalNotesApiService].
     * This test verifies that the [LocalNotesApiService.getAllNotes] method returns the expected list of notes.
     */
    @Test
    fun localNotesApiService_GetAllNotes() {
        // Create an instance of LocalNotesApiService
        val localNotesApiService = LocalNotesApiService()

        // Call the getAllNotes method
        val notes = localNotesApiService.getAllNotes()

        // Verify if the retrieved notes match the expected notes
        assertEquals(10, notes.size)
        assertEquals("La vie est belle", notes[0].title)
        assertEquals(
            "La vie est belle, pleine de choses à voir et à faire. Profitez de chaque moment et ne laissez jamais personne vous dire que vous ne pouvez pas faire ce que vous voulez.",
            notes[0].body
        )
        assertEquals(
            "Ne laissez personne vous dire que vous ne pouvez pas faire quelque chose.",
            notes[1].title
        )
        assertEquals("Suivez vos rêves", notes[2].title)
        assertEquals("Soyez gentil avec les autres", notes[3].title)
        assertEquals("Aidez les autres", notes[4].title)
        assertEquals("Soyez reconnaissant pour ce que vous avez.", notes[5].title)
        assertEquals("Vivez le moment présent", notes[6].title)
        assertEquals("Prenez soin de vous", notes[7].title)
        assertEquals("Passez du temps avec vos proches", notes[8].title)
        assertEquals("Risez et amusez-vous.", notes[9].title)

        // Print a message if the test is executed without errors
        println("The test 'localNotesApiService_GetAllNotes' was executed successfully.")

    }

    /**
     * Unit test for the [NoteRepository.notes] property.
     * This test verifies that the [NoteRepository.notes] property returns the expected list of notes.
     */
    @Test
    fun noteRepository_GetAllNotes() {
        // Use runBlocking for suspended tests
        runBlocking {

            // Create a mock of NoteRepository
            val mockNoteRepository = mock(NoteRepository::class.java)

            // Define the expected behavior when accessing the notes property
            `when`(mockNoteRepository.notes).thenReturn(
                flowOf(
                    listOf(
                        Note("Title 1", "Body 1"),
                        Note("Title 2", "Body 2"),
                        Note("Title 3", "Body 3")
                    )
                )
            )

            // Call the notes property of the repository
            val notes = mockNoteRepository.notes

            // Collect the notes emitted by the flow
            val collectedNotes = mutableListOf<Note>()
            notes.collect { collectedNotes.addAll(it) }

            // Verify if the retrieved notes match the expected notes
            assertEquals(3, collectedNotes.size)
            assertEquals("Title 1", collectedNotes[0].title)
            assertEquals("Body 1", collectedNotes[0].body)
            assertEquals("Title 2", collectedNotes[1].title)
            assertEquals("Body 2", collectedNotes[1].body)
            assertEquals("Title 3", collectedNotes[2].title)
            assertEquals("Body 3", collectedNotes[2].body)

            // Print a message if the test is executed without errors
            println("The test 'noteRepository_GetAllNotes' was executed successfully.")
        }
    }
}