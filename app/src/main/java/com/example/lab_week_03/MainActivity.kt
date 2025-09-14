package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView

// The duplicate interface definition has been removed from this file.
// The class now correctly implements the interface from its own file, 'CoffeeListener.kt'.

class MainActivity : AppCompatActivity(), CoffeeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set window insets listener for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add the ListFragment only when the app is first created.
        // This 'if' block prevents a new fragment from being added every time the
        // screen rotates or the activity is recreated.
        if (savedInstanceState == null) {
            findViewById<FragmentContainerView>(R.id.fragment_container).let { containerLayout ->
                val listFragment = ListFragment()
                supportFragmentManager.beginTransaction()
                    .add(containerLayout.id, listFragment)
                    .commit()
            }
        }
    }

    /**
     * This method is required by the CoffeeListener interface.
     * It's called by the ListFragment whenever a coffee is clicked.
     * This implementation replaces the ListFragment with a new DetailFragment.
     */
    override fun onSelected(id: Int) {
        findViewById<FragmentContainerView>(R.id.fragment_container).let { containerLayout ->
            // Create a new instance of DetailFragment using the recommended factory method
            val detailFragment = DetailFragment.newInstance(id)

            // Replace the current fragment with the new DetailFragment
            supportFragmentManager.beginTransaction()
                .replace(containerLayout.id, detailFragment)
                .addToBackStack(null) // This allows the user to press the back button to return to the list
                .commit()
        }
    }
}