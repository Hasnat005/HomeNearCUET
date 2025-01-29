package com.example.tablayout


data class Property(
    val title: String,
    val location: String,
    val price: Int,
    val details: String,
    val landownerId: String,
    val imageResId: Int, // Image resource ID for the property image
    val googleMapsLink: String, // Optional Google Maps link for the property location
    val latitude: Double, // Latitude of the property
    val longitude: Double // Longitude of the property
)

