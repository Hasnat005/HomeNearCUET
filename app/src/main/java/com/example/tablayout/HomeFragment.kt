package com.example.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayout.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var propertyAdapter: PropertyAdapter
    private lateinit var propertyList: List<Property>
    private var _binding: FragmentHomeBinding? = null // ViewBinding reference
    private val binding get() = _binding!! // Non-nullable reference

    private val currentLandownerId = "12345" // Simulating a logged-in user's ID (replace with session logic)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Populate property list with sample data near CUET
        propertyList = listOf(
            Property(
                title = "2-Bedroom Apartment Near CUET",
                location = "CUET Road, Chattogram",
                price = 7000,
                imageResId = R.drawable.property1,
                latitude = 22.4591,
                longitude = 91.9692,
                googleMapsLink = "https://www.google.com/maps",
                details = "A spacious 2-bedroom apartment with modern amenities, located just 5 minutes from CUET.",
                landownerId = "4444" // Owned by the logged-in landowner
            ),
            Property(
                title = "Cozy Studio for Students",
                location = "Gate No. 1, CUET",
                price = 5000,
                imageResId = R.drawable.property2,
                latitude = 22.4593,
                longitude = 91.9695,
                googleMapsLink = "https://www.google.com/maps",
                details = "Perfect for students, this studio includes Wi-Fi, a single bedroom, and easy access to CUET.",
                landownerId = "67890" // Owned by another landowner (student cannot edit this)
            ),
            Property(
                title = "Modern 3-Bedroom House",
                location = "Sholoshahar Road, CUET Area",
                price = 12000,
                imageResId = R.drawable.property3,
                latitude = 22.4587,
                longitude = 91.9689,
                googleMapsLink = "https://www.google.com/maps",
                details = "A fully furnished 3-bedroom house with a garden and parking space, ideal for families near CUET.",
                landownerId = "4444" // Owned by the logged-in landowner
            )
        )

        // Initialize PropertyAdapter with current landowner ID
        propertyAdapter = PropertyAdapter(propertyList, requireContext(), currentLandownerId)

        // Attach the adapter to RecyclerView
        binding.recyclerView.adapter = propertyAdapter

        return binding.root // Return the root view from ViewBinding
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to avoid memory leaks
    }
}
