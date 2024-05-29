package com.openclassrooms.notes.di

import com.openclassrooms.notes.data.repository.NoteRepository
import com.openclassrooms.notes.ui.notes.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * This module is responsible for configuring the dependency injection for the application.
 *
 * It provides various dependencies such as repositories, view models, and network services
 * required by different parts of the application.
 *
 * By using this module, we ensure that dependencies are provided in a consistent and
 * efficient manner, facilitating easier testing and better separation of concerns.
 */
val appModule = module {
    viewModelOf(::NoteViewModel)
    singleOf(::NoteRepository)
}
