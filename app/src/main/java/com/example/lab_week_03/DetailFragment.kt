package com.example.lab_week_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameTextView = view.findViewById<TextView>(R.id.coffee_name)
        val descriptionTextView = view.findViewById<TextView>(R.id.coffee_description)

        // The 'when' statement now checks the ID passed from ListFragment
        when (arguments?.getInt(ListFragment.COFFEE_ID)) {
            R.id.affogato -> {
                nameTextView.text = getString(R.string.affogato)
                descriptionTextView.text = getString(R.string.affogato_desc)
            }
            R.id.americano -> {
                nameTextView.text = getString(R.string.americano)
                descriptionTextView.text = getString(R.string.americano_desc)
            }
            R.id.latte -> {
                nameTextView.text = getString(R.string.latte)
                descriptionTextView.text = getString(R.string.latte_desc)
            }
            // ADDED: Cases for the new coffee items
            R.id.cappuccino -> {
                nameTextView.text = getString(R.string.cappuccino)
                descriptionTextView.text = getString(R.string.cappuccino_desc)
            }
            R.id.espresso -> {
                nameTextView.text = getString(R.string.espresso)
                descriptionTextView.text = getString(R.string.espresso_desc)
            }
        }

        // ADDED: Handle click listener for the new back button
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            // Use NavController to go back to the previous fragment in the back stack
            findNavController().popBackStack()
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(coffeeId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ListFragment.COFFEE_ID, coffeeId)
                }
            }
    }
}