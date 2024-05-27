package com.openclassrooms.notes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.ui.notes.NoteFragment
import com.openclassrooms.notes.ui.notes.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main activity for the app.
 */
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    private val noteViewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NoteFragment())
                .commitNow()
        }
    }
}
