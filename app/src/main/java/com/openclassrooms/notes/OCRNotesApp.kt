package com.openclassrooms.notes

import android.app.Application
import com.openclassrooms.notes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * The application class for OCRNotes.
 */
class OCRNotesApp : Application() {
    /**
     * Called when the application is starting, before any other application objects have been created.
     */
    override fun onCreate() {
        super.onCreate()

        // Start Koin for dependency injection.
        startKoin {
            // Set the Android context for Koin.
            androidContext(this@OCRNotesApp)
            // Load the Koin module defined in appModule.
            modules(appModule)
        }
    }
}