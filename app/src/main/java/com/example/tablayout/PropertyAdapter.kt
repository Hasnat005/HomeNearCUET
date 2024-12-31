package com.example.tablayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertyAdapter(private var propertyList: List<Property>) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    class PropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvPropertyTitle)
        val details: TextView = view.findViewById(R.id.tvPropertyDetails)
        val price: TextView = view.findViewById(R.id.tvPropertyPrice)
        val image: ImageView = view.findViewById(R.id.ivPropertyImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.title.text = property.title
        holder.details.text = "${property.location}, Wi-Fi included"
        holder.price.text = "৳ ${property.price}"
        holder.image.setImageResource(property.imageResId)
    }

    override fun getItemCount(): Int = propertyList.size
    fun updateList(filteredList: List<Property>) {
        propertyList = filteredList
        notifyDataSetChanged()
    }
}
