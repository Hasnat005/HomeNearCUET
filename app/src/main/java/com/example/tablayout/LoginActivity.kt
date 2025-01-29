package com.example.tablayout

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var spinnerUserType: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        spinnerUserType = findViewById(R.id.spinnerUserType)

        // Setup user type spinner
        val userTypes = arrayOf("Student", "Landowner")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, userTypes)
        spinnerUserType.adapter = adapter

        // Initialize default credentials if not set
        val sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE)
        if (!sharedPreferences.contains("studentEmail")) {
            sharedPreferences.edit {
                putString("studentEmail", "student@gmail.com")
                putString("studentPassword", "123456")
            }
        }
        if (!sharedPreferences.contains("landownerEmail")) {
            sharedPreferences.edit {
                putString("landownerEmail", "landowner@gmail.com")
                putString("landownerPassword", "123456")
            }
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val userType = spinnerUserType.selectedItem.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val storedEmail = sharedPreferences.getString("${userType.lowercase()}Email", "")
            val storedPassword = sharedPreferences.getString("${userType.lowercase()}Password", "")

            if (email == storedEmail && password == storedPassword) {
                // Login successful
                sharedPreferences.edit {
                    putBoolean("isLoggedIn", true)
                    putString("currentUserType", userType)
                }

                // Redirect to the respective MainActivity based on user type
                val intent = if (userType == "Student") {
                    Intent(this, MainActivity::class.java)
                } else {
                    Intent(this, LandownerMainActivity::class.java)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Open RegisterActivity when Register button is clicked
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
