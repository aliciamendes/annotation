package com.example.annotation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.annotation.Model.User
import com.example.annotation.ViewModel.UserViewModel
import com.example.annotation.ui.Fragments.HomeFragment
import kotlin.math.log

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
        if(nameUser.isNotEmpty()){
            viewModel.addName(
                User(
                    id = null,
                    nameUser = nameUser
                ))
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else {
            Toast.makeText(this, "Por favor informe seu nome", Toast.LENGTH_SHORT).show()
        }
    }


    private fun skippingScreen(){
        val nameUser = viewModel.getName()
        if(nameUser != null ){
            if (nameUser.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }





}