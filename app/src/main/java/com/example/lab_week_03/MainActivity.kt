package com.example.lab_week_03

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// The MainActivity now implements the CoffeeListener interface
class MainActivity : AppCompatActivity(), CoffeeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "MainActivity: onCreate")
    }

    // This is the required method from the CoffeeListener interface.
    // It's called by ListFragment when an item is clicked.
    override fun onSelected(id: Int) {
        // Find the DetailFragment instance from the layout
        val detailFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_detail) as DetailFragment

        // Call the public method in DetailFragment to update its UI
        detailFragment.setCoffeeData(id)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity: onDestroy")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}