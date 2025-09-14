package com.example.lab_week_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// The old fragment initialization parameters, e.g. ARG_ITEM_NUMBER
// These are no longer used by the new newInstance method, but we can leave them for now.
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val coffeeTitle: TextView?
        get() = view?.findViewById(R.id.coffee_title)

    private val coffeeDesc: TextView?
        get() = view?.findViewById(R.id.coffee_desc)

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
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    // --- THIS IS THE UPDATED PART ---
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the coffee ID from arguments, default to 0 if not found
        val coffeeId = arguments?.getInt(COFFEE_ID, 0) ?: 0
        // Set the coffee data based on the ID
        setCoffeeData(coffeeId)
    }

    /**
     * This function is called by the MainActivity to update the UI
     * with the details of the selected coffee.
     */
    fun setCoffeeData(id: Int) {
        when (id) {
            R.id.affogato -> {
                coffeeTitle?.text = getString(R.string.affogato_title)
                coffeeDesc?.text = getString(R.string.affogato_desc)
            }
            R.id.americano -> {
                coffeeTitle?.text = getString(R.string.americano_title)
                coffeeDesc?.text = getString(R.string.americano_desc)
            }
            R.id.latte -> {
                coffeeTitle?.text = getString(R.string.latte_title)
                coffeeDesc?.text = getString(R.string.latte_desc)
            }
            else -> {
                // Default case: show placeholder text if ID is invalid or 0
                coffeeTitle?.text = getString(R.string.placeholder_title)
                coffeeDesc?.text = getString(R.string.placeholder_desc)
            }
        }
    }

    companion object {
        private const val COFFEE_ID = "COFFEE_ID"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided coffee ID.
         */
        fun newInstance(coffeeId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(COFFEE_ID, coffeeId)
                }
            }
    }
}