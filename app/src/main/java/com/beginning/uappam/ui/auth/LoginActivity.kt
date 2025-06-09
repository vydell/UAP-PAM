package com.beginning.uappam.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.beginning.uappam.databinding.ActivityLoginBinding
import com.beginning.uappam.ui.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var contentBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)
        auth = FirebaseAuth.getInstance()

        contentBinding.btnLogin.setOnClickListener {
            val email = contentBinding.etEmail.text.toString().trim()
            val password = contentBinding.etPass.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intentHome = Intent(this, HomeActivity::class.java).apply {
                            putExtra("USER_EMAIL", email)
                        }
                        startActivity(intentHome)
                        finish()
                    }
                }
        }
    }
}