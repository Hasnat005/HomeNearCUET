package com.example.tablayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditPropertyActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etLocation: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDetails: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_property)

        // Initialize views
        etTitle = findViewById(R.id.etTitle)
        etLocation = findViewById(R.id.etLocation)
        etPrice = findViewById(R.id.etPrice)
        etDetails = findViewById(R.id.etDetails)
        btnSave = findViewById(R.id.btnSave)

        // Get property details from the intent
        val title = intent.getStringExtra("title")
        val location = intent.getStringExtra("location")
        val price = intent.getIntExtra("price", 0)
        val details = intent.getStringExtra("details")
        val landownerId = intent.getStringExtra("landownerId") // To verify ownership if needed

        // Pre-fill the fields with current property details
        etTitle.setText(title)
        etLocation.setText(location)
        etPrice.setText(price.toString())
        etDetails.setText(details)

        // Handle the Save button click
        btnSave.setOnClickListener {
            // Get updated property details
            val updatedTitle = etTitle.text.toString()
            val updatedLocation = etLocation.text.toString()
            val updatedPrice = etPrice.text.toString().toIntOrNull() ?: 0
            val updatedDetails = etDetails.text.toString()

            // Perform validation (optional)
            if (updatedTitle.isEmpty() || updatedLocation.isEmpty() || updatedPrice <= 0 || updatedDetails.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields correctly.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simulate updating the property (e.g., send to server or database)
            // For now, just show a toast
            Toast.makeText(this, "Property updated successfully!", Toast.LENGTH_SHORT).show()

            // Close the activity
            finish()
        }
    }
}
