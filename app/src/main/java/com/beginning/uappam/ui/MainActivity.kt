package com.beginning.uappam.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beginning.uappam.databinding.ActivityMainBinding
import com.beginning.uappam.ui.auth.LoginActivity
import com.beginning.uappam.ui.auth.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var contentBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        contentBinding.tvRegister.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }

        contentBinding.btnLogin.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}