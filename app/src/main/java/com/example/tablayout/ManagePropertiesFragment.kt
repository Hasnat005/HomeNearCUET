package com.example.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ManagePropertiesFragment : Fragment() {

    private lateinit var propertyAdapter: PropertyAdapter
    private lateinit var propertyList: MutableList<Property>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_manage_properties, container, false)

        // Reference RecyclerView from the layout
        val recyclerView: RecyclerView = view.findViewById(R.id.rvManageProperties)

        // Fetch the properties
        propertyList = getLandownerProperties()

        // Set up the RecyclerView
        propertyAdapter = PropertyAdapter(propertyList, requireContext(), "12345") // Pass the current landownerId
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = propertyAdapter

        return view
    }

    // Updated function to return the property list as per your details
    private fun getLandownerProperties(): MutableList<Property> {
        return mutableListOf(
            Property(
                "2-Bedroom Apartment Near CUET",
                "CUET Road, Chattogram",
                7000,
                "A spacious 2-bedroom apartment with modern amenities, located just 5 minutes from CUET.",
                "012345",
                R.drawable.property1,
                "https://www.google.com/maps",
                22.4591,
                91.9692
            ),
            Property(
                "Cozy Studio for Students",
                "Gate No. 1, CUET",
                5000,
                "Perfect for students, this studio includes Wi-Fi, a single bedroom, and easy access to CUET.",
                "67890",
                R.drawable.property2,
                "https://www.google.com/maps",
                22.4593,
                91.9695
            ),
            Property(
                "Modern 3-Bedroom House",
                "Sholoshahar Road, CUET Area",
                12000,
                "A fully furnished 3-bedroom house with a garden and parking space, ideal for families near CUET.",
                "012345",
                R.drawable.property3,
                "https://www.google.com/maps",
                22.4587,
                91.9689
            )
        )
    }
}
