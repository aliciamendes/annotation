package com.example.annotation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import com.example.annotation.Model.User
import com.example.annotation.ViewModel.UserViewModel

class WelcomeScreen : AppCompatActivity() {

    val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        supportActionBar?.hide()
        skippingScreen()

        findViewById<Button>(R.id.button_continue).setOnClickListener {
            addNameUserStorage()
        }
    }

    private fun addNameUserStorage(){
        val nameUser = findViewById<TextView>(R.id.name_edit).text.toString()

        viewModel.addName(
            User(
                id = null,
                nameUser = nameUser
                ))

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun skippingScreen() {
        val name = viewModel.getName()

        if(name != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}