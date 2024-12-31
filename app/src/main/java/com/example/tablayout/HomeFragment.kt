package com.example.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var rvAllProperties: RecyclerView
    private lateinit var allPropertiesAdapter: PropertyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        rvAllProperties = view.findViewById(R.id.rvAllProperties)

        // Setup RecyclerView Adapter
        allPropertiesAdapter = PropertyAdapter(getAllProperties())
        rvAllProperties.layoutManager = LinearLayoutManager(requireContext())
        rvAllProperties.adapter = allPropertiesAdapter

        return view
    }

    // Dummy Data for All Properties
    private fun getAllProperties(): List<Property> {
        return listOf(
            Property("City Apartment", "CUET", 4000, R.drawable.property1),
            Property("Farmhouse", "CUET", 7000, R.drawable.property2),
            Property("Studio Apartment", "CUET", 3000, R.drawable.property3)
        )
    }
}
