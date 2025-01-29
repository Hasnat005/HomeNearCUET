package com.example.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayout.databinding.FragmentLandownerHomeBinding

class LandownerHomeFragment : Fragment() {

    private var _binding: FragmentLandownerHomeBinding? = null
    private val binding get() = _binding!!

    // Assuming you have a way to identify if the user is a landowner
    private var currentLandownerId: String = "12345" // Replace this with actual logged-in user ID

    private lateinit var propertyAdapter: PropertyAdapter
    private lateinit var landownerProperties: List<Property>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandownerHomeBinding.inflate(inflater, container, false)

        // Get properties for the current landowner
        landownerProperties = getLandownerProperties(currentLandownerId)

        // Set up the RecyclerView with the landowner's properties
        propertyAdapter = PropertyAdapter(landownerProperties, requireContext(), currentLandownerId)
        binding.recyclerViewProperties.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProperties.adapter = propertyAdapter

        return binding.root
    }

    private fun getLandownerProperties(landownerId: String): List<Property> {
        // Replace this logic with the actual database query or API call to fetch properties for the current landowner
        val allProperties = listOf(
            Property(
                "2-Bedroom Apartment Near CUET",
                "CUET Road, Chattogram",
                7000,
                "A spacious 2-bedroom apartment with modern amenities, located just 5 minutes from CUET.",
                "12345",
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
                "12345",
                R.drawable.property3,
                "https://www.google.com/maps",
                22.4587,
                91.9689
            )
        )


        // Filter properties that belong to the current landowner
        return allProperties.filter { it.landownerId == landownerId }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
