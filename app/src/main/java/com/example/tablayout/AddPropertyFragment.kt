package com.example.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tablayout.databinding.FragmentAddPropertyBinding

class AddPropertyFragment : Fragment() {

    private var _binding: FragmentAddPropertyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the binding layout
        _binding = FragmentAddPropertyBinding.inflate(inflater, container, false)

        // Set up click listener for the Add Property button
        binding.addPropertyButton.setOnClickListener {
            val name = binding.propertyName.text.toString().trim()
            val location = binding.propertyLocation.text.toString().trim()
            val price = binding.propertyPrice.text.toString().trim().toIntOrNull()

            // Validate inputs and show appropriate messages
            if (validatePropertyDetails(name, location, price)) {
                saveProperty(name, location, price!!)
                Toast.makeText(requireContext(), "Property Added Successfully", Toast.LENGTH_SHORT).show()
                clearInputs(binding.propertyName, binding.propertyLocation, binding.propertyPrice)
            } else {
                showValidationError(name, location, price)
            }
        }

        return binding.root
    }

    // Function to validate property details
    private fun validatePropertyDetails(name: String, location: String, price: Int?): Boolean {
        return name.isNotEmpty() && location.isNotEmpty() && price != null && price > 0
    }

    // Function to save the property (for now, it's a placeholder)
    private fun saveProperty(name: String, location: String, price: Int) {
        // Implement property saving logic here (e.g., save to a database or SharedPreferences)
    }

    // Clear input fields after successful submission
    private fun clearInputs(vararg fields: EditText) {
        fields.forEach { it.text.clear() }
    }

    // Function to show validation error messages
    private fun showValidationError(name: String, location: String, price: Int?) {
        when {
            name.isEmpty() -> Toast.makeText(requireContext(), "Property Name cannot be empty", Toast.LENGTH_SHORT).show()
            location.isEmpty() -> Toast.makeText(requireContext(), "Property Location cannot be empty", Toast.LENGTH_SHORT).show()
            price == null -> Toast.makeText(requireContext(), "Price must be a valid number", Toast.LENGTH_SHORT).show()
            price <= 0 -> Toast.makeText(requireContext(), "Price must be greater than zero", Toast.LENGTH_SHORT).show()
        }
    }

    // Clean up binding when view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
