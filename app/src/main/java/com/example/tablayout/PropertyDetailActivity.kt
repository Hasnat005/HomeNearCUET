package com.example.tablayout

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class PropertyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        val title: TextView = findViewById(R.id.tvPropertyDetailTitle)
        val location: TextView = findViewById(R.id.tvPropertyDetailLocation)
        val price: TextView = findViewById(R.id.tvPropertyDetailPrice)
        val image: ImageView = findViewById(R.id.ivPropertyDetailImage)
        val googleMapsButton: Button = findViewById(R.id.btnOpenGoogleMaps)
        val roomDetails: TextView = findViewById(R.id.tvRoomDetails)

        // Set up the toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        // Get data from Intent
        val propertyTitle = intent.getStringExtra("title") ?: "Property Details"
        val propertyLocation = intent.getStringExtra("location") ?: "Unknown Location"
        val propertyPrice = intent.getStringExtra("price") ?: "0"
        val imageResId = intent.getIntExtra("imageResId", R.drawable.placeholder_image)
        val googleMapsLink = intent.getStringExtra("googleMapsLink") ?: "https://www.google.com/maps" // Default link

        // Get additional data like latitude and longitude for Google Maps
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        // Set data to views
        collapsingToolbar.title = propertyTitle
        title.text = propertyTitle
        location.text = propertyLocation
        price.text = "৳ $propertyPrice"
        image.setImageResource(imageResId)

        // Set the room details
        val additionalRoomInfo = "This is a spacious 3-bedroom apartment with Wi-Fi and air conditioning."
        roomDetails.text = additionalRoomInfo

        // Open Google Maps when the button is clicked
        googleMapsButton.setOnClickListener {
            openGoogleMaps(latitude, longitude)
        }
    }

    // Method to open Google Maps using the latitude and longitude from the intent
    private fun openGoogleMaps(latitude: Double, longitude: Double) {
        if (latitude != 0.0 && longitude != 0.0) {
            val uri = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        } else {
            // Handle the case where the coordinates are not valid
            val uri = Uri.parse("https://www.google.com/maps")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}


