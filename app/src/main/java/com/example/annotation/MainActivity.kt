package com.example.annotation

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)
        supportActionBar?.hide()
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

}