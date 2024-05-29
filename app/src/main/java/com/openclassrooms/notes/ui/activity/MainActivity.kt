package com.openclassrooms.notes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.ui.notes.NoteFragment

/**
 * The main activity for the app.
 *
 * This activity serves as the entry point for the application. It initializes the main layout
 * and sets up the initial fragment to display the list of notes.
 */
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     *
     * This property is used to access the views defined in the activity's layout file.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Called when the activity is first created.
     *
     * This method initializes the activity, sets up the layout binding, and loads the initial
     * fragment if there is no saved instance state.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the NoteFragment if this is the first creation
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NoteFragment())
                .commitNow()
        }
    }
}
