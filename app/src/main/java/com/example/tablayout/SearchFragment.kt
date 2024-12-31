package com.example.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class SearchFragment : Fragment() {

    private lateinit var rvSearchResults: RecyclerView
    private lateinit var etSearchBar: EditText
    private lateinit var spinnerSort: Spinner
    private lateinit var searchAdapter: PropertyAdapter
    private lateinit var allProperties: List<Property>
    private lateinit var tvResults: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Initialize Views
        rvSearchResults = view.findViewById(R.id.rvSearchResults)
        etSearchBar = view.findViewById(R.id.etSearch)
        spinnerSort = view.findViewById(R.id.spinnerSortBy)
        tvResults = view.findViewById(R.id.tvResults)

        // Load Property Data
        allProperties = getAllProperties()

        // Setup Spinner for Sorting
        val sortOptions = arrayOf("Price: Low to High", "Price: High to Low")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sortOptions)
        spinnerSort.adapter = adapter

        // Setup RecyclerView
        searchAdapter = PropertyAdapter(allProperties)
        rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        rvSearchResults.adapter = searchAdapter

        // Add Search Functionality
        etSearchBar.addTextChangedListener {
            filterProperties(it.toString())
        }

        // Add Sorting Functionality
        spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> sortPropertiesAscending()
                    2 -> sortPropertiesDescending()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return view
    }

    private fun filterProperties(query: String) {
        val filteredList = allProperties.filter {
            it.title.contains(query, ignoreCase = true) || it.location.contains(query, ignoreCase = true)
        }
        searchAdapter.updateList(filteredList)
        updateResultsCount(filteredList.size)
    }

    private fun sortPropertiesAscending() {
        val sortedList = allProperties.sortedBy { it.price }
        searchAdapter.updateList(sortedList)
        updateResultsCount(sortedList.size)
    }

    private fun sortPropertiesDescending() {
        val sortedList = allProperties.sortedByDescending { it.price }
        searchAdapter.updateList(sortedList)
        updateResultsCount(sortedList.size)
    }

    private fun updateResultsCount(count: Int) {
        tvResults.text = "Results available: $count rooms"
    }

    private fun getAllProperties(): List<Property> {
        return listOf(
            Property("GorisonkorHat", "CUET", 300, R.drawable.property1),
            Property("Cuet Gate", "CUET", 1000, R.drawable.property2),
            Property("Pahartoli", "CUET", 550, R.drawable.property3),
            Property("Nowapara", "CUET", 1200, R.drawable.property4)
        )
    }
}
