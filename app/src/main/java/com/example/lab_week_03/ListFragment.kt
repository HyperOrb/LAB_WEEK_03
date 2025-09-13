package com.example.lab_week_03

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// Define the interface for communication
interface CoffeeListener {
    fun onSelected(coffeeId: Int)
}

// Make the class implement View.OnClickListener
class ListFragment : Fragment(), View.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null

    // This listener will be our connection to the MainActivity
    private lateinit var coffeeListener: CoffeeListener

    override fun onAttach(context: Context) {
        // ... the rest of your file remains the same
        super.onAttach(context)
        // This makes sure the host activity has implemented the listener
        if (context is CoffeeListener) {
            coffeeListener = context
        } else {
            throw RuntimeException("Must implement CoffeeListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get a list of all the coffee TextViews
        val coffeeList = listOf<View>(
            view.findViewById(R.id.affogato),
            view.findViewById(R.id.americano),
            view.findViewById(R.id.latte)
        )

        // Set the click listener for each item in the list
        coffeeList.forEach {
            it.setOnClickListener(this)
        }
    }

    // This method is called when any of the TextViews are clicked
    override fun onClick(v: View?) {
        v?.let { coffee ->
            // Use the listener to send the ID of the clicked coffee to the MainActivity
            coffeeListener.onSelected(coffee.id)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}