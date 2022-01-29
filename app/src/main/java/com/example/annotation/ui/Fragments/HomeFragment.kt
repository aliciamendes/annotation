package com.example.annotation.ui.Fragments

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import android.text.format.DateFormat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.ViewModel.UserViewModel
import com.example.annotation.databinding.FragmentHomeBinding
import com.example.annotation.ui.Adapter.NotesAdapter
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var greetings: String
    private lateinit var nameUser: String

    private val viewModel: NotesViewModel by viewModels()
    private val viewModelUser: UserViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        greetings = getTimeCourse()
        nameUser = getNameU()

        binding.nameUser.text = "$greetings, $nameUser"

        viewModel.getNotes().observe(
            viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            })

        createNotificationChannel()

        binding.buttonAddNote.setOnClickListener {
            builderNotification()
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createAnnotationFragment)
        }

        binding.imageUser.setOnClickListener{
            if (checkCameraHardware()){
                checkPermissionCamera()
            }
        }
        return binding.root
    }

    private fun getNameU(): String {
        return viewModelUser.getName()
    }

    private fun checkPermissionCamera(){
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), 0)
        }
    }

    private fun getTimeCourse(): String {
        val timeNowInt: Int = DateFormat.format("H", Date().time).toString().toInt()
        return when (timeNowInt) {
            in 0..12 -> {
                "Bom dia"
            }
            in 13..18 -> {
                "Boa tarde"
            }
            else -> {
                "Boa noite"
            }
        }
    }

    private fun checkCameraHardware(): Boolean {
        return context?.packageManager!!.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    private fun builderNotification(){
        val intent = Intent(requireActivity(), HomeFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)

        val builder = NotificationCompat.Builder(requireContext(), "1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Text")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setFullScreenIntent(pendingIntent, true)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColor(32423)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        with(NotificationManagerCompat.from(requireContext())){
            notify(id, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = "channel_annotation"
            val descriptionText = "channel_description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(id.toString(), name, importance).apply {
                description = descriptionText
            }

            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = Color.BLUE

            val notificationManager: NotificationManager =
                activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
