package com.example.tablayout

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LandownerMainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var landownerPagerAdapter: LandownerPagerAdapter
    private lateinit var tabLayout: TabLayout

    private val tabsArray = arrayOf("Manage Properties", "Add Property", "Home", "Profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is logged in
        if (!isUserLoggedIn()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_landowner_main)

        // Initialize the ViewPager2 and TabLayout
        viewPager2 = findViewById(R.id.viewPager2)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // Set up the adapter for the ViewPager2
        landownerPagerAdapter = LandownerPagerAdapter(supportFragmentManager, lifecycle)
        landownerPagerAdapter.addFragmentToList(ManagePropertiesFragment()) // Manage Properties
        landownerPagerAdapter.addFragmentToList(AddPropertyFragment()) // Add Property
        landownerPagerAdapter.addFragmentToList(LandownerHomeFragment()) // Home for Landowners
        landownerPagerAdapter.addFragmentToList(LandownerProfileFragment()) // Profile

        // Set the adapter to the ViewPager2
        viewPager2.adapter = landownerPagerAdapter

        // Initialize the TabLayout and set icons for tabs
        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.baseline_home_24)
                1 -> tab.setIcon(R.drawable.baseline_add_24)
                2 -> tab.setIcon(R.drawable.baseline_list_24)
                3 -> tab.setIcon(R.drawable.baseline_person_24)
            }
        }.attach()

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Check if the user is logged in from SharedPreferences
    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
