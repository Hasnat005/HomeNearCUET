package com.example.tablayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    lateinit var myAdapter: MyPagerAdapter
    lateinit var tabLayout: TabLayout

    var tabsArray = arrayOf("Home", "Search", "Notification", "Profile")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Force the app to always stay in light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        setContentView(R.layout.activity_main)

        viewPager2 = findViewById(R.id.viewPager2)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myAdapter = MyPagerAdapter(supportFragmentManager, lifecycle)
        myAdapter.addFragmentToList(HomeFragment())
        myAdapter.addFragmentToList(SearchFragment())
        myAdapter.addFragmentToList(NotificationFragment())
        myAdapter.addFragmentToList(ProfileFragment())

        viewPager2.adapter = myAdapter

        tabLayout = findViewById(R.id.tablayout);
        tabLayout = findViewById(R.id.tablayout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.baseline_home_24)
                1 -> tab.setIcon(R.drawable.baseline_search_24)
                2 -> tab.setIcon(R.drawable.baseline_notifications_24)
                3 -> tab.setIcon(R.drawable.baseline_person_24)
            }
        }.attach()






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}