package com.example.tablayout

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertyAdapter(
    private var propertyList: List<Property>,
    private val context: Context,
    private val currentLandownerId: String // Logged-in user's ID
) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.bind(property)

        // Set click listener on itemView for property details
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PropertyDetailActivity::class.java).apply {
                putExtra("title", property.title)
                putExtra("location", property.location)
                putExtra("price", property.price)
                putExtra("imageResId", property.imageResId)
                putExtra("googleMapsLink", property.googleMapsLink)
                putExtra("latitude", property.latitude) // Latitude from the property object
                putExtra("longitude", property.longitude) // Longitude from the property object
            }
            context.startActivity(intent)
        }

        // Check if the current user is the owner of this property
        if (property.landownerId == currentLandownerId) {
            // Show the edit button for landowner's properties
            holder.btnEditProperty.visibility = View.VISIBLE
            holder.btnEditProperty.setOnClickListener {
                // Launch EditPropertyActivity with the property details
                val intent = Intent(context, EditPropertyActivity::class.java).apply {
                    putExtra("title", property.title)
                    putExtra("location", property.location)
                    putExtra("price", property.price)
                    putExtra("details", property.details)
                    putExtra("landownerId", property.landownerId)
                }
                context.startActivity(intent)
            }
        } else {
            // Hide the edit button for non-landowner properties (students)
            holder.btnEditProperty.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = propertyList.size

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPropertyImage: ImageView = itemView.findViewById(R.id.ivPropertyImage)
        private val tvPropertyTitle: TextView = itemView.findViewById(R.id.tvPropertyTitle)
        private val tvPropertyDetails: TextView = itemView.findViewById(R.id.tvPropertyDetails)
        private val tvPropertyPrice: TextView = itemView.findViewById(R.id.tvPropertyPrice)
        val btnEditProperty: Button = itemView.findViewById(R.id.btnEditProperty)

        fun bind(property: Property) {
            ivPropertyImage.setImageResource(property.imageResId)
            tvPropertyTitle.text = property.title
            tvPropertyDetails.text = property.details
            tvPropertyPrice.text = "৳ ${property.price}"
        }
    }

    // Method to update the list of properties in the adapter
    fun updateList(newList: List<Property>) {
        propertyList = newList
        notifyDataSetChanged() // Refresh the RecyclerView with the new list
    }
}
