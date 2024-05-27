package com.openclassrooms.notes.di

import com.openclassrooms.notes.data.repository.NoteRepository
import com.openclassrooms.notes.ui.notes.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NoteRepository() }
    viewModel { NoteViewModel(get()) }
}
