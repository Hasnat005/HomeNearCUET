package com.example.tablayout

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay and navigate based on login status
        Handler().postDelayed({
            val sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)


//            val nextActivity = if (isLoggedIn) {
//                MainActivity::class.java
//            } else {
//                LoginActivity::class.java
//            }


            val nextActivity = MainActivity::class.java

            val intent = Intent(this, nextActivity)
            startActivity(intent)
            finish()
        }, 2000) // 2-second delay
    }
}
