package com.example.tablayout


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    // Replace these values with real user data
    private val userName = "John Doe"
    private val userId = "12345678"
    private val userEmail = "johndoe@example.com"
    private val userPhone = "+1 (555) 123-4567"
    private val userRoomType = "Shared"
    private val userWiFi = "Included"
    private val userProfileImage = R.drawable.profile_image // Placeholder image

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Find views by ID
        val profileImage: ImageView = rootView.findViewById(R.id.profile_image)
        val profileName: TextView = rootView.findViewById(R.id.profile_name)
        val profileId: TextView = rootView.findViewById(R.id.profile_id)
        val profileEmail: TextView = rootView.findViewById(R.id.profile_email)
        val profilePhone: TextView = rootView.findViewById(R.id.profile_phone)
        val profileRoomType: TextView = rootView.findViewById(R.id.profile_room_type)
        val profileWiFi: TextView = rootView.findViewById(R.id.profile_wifi)
        val btnEditProfile: Button = rootView.findViewById(R.id.btn_edit_profile)
        val btnChangePassword: Button = rootView.findViewById(R.id.btn_change_password)
        val btnLogout: Button = rootView.findViewById(R.id.btn_logout)

        // Set user data to the views
        profileImage.setImageResource(userProfileImage)
        profileName.text = userName
        profileId.text = "Student ID: $userId"
        profileEmail.text = "Email: $userEmail"
        profilePhone.text = "Phone: $userPhone"
        profileRoomType.text = "Room Type: $userRoomType"
        profileWiFi.text = "Wi-Fi: $userWiFi"

        // Set button click listeners
        btnEditProfile.setOnClickListener {
            Toast.makeText(context, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
            // Add navigation or logic for editing the profile
        }

        btnChangePassword.setOnClickListener {
            Toast.makeText(context, "Change Password clicked", Toast.LENGTH_SHORT).show()
            // Add navigation or logic for changing the password
        }

        // Logout button functionality
        btnLogout.setOnClickListener {
            // Clear login state
            val sharedPreferences = requireActivity().getSharedPreferences("appPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putBoolean("isLoggedIn", false)
            }

            // Redirect to LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }

        return rootView
    }
}
