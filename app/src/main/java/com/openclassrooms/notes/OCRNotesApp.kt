package com.openclassrooms.notes

import android.app.Application
import com.openclassrooms.notes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OCRNotesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@OCRNotesApp)
            modules(appModule)
        }
    }

}