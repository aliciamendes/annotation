package com.example.annotation.ui.Fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.annotation.Model.Notes
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.databinding.FragmentCreateAnnotationBinding
import com.google.android.gms.location.*
import java.util.*

class CreateAnnotationFragment : Fragment() {

    private val viewModel: NotesViewModel by viewModels()
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: FragmentCreateAnnotationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateAnnotationBinding.inflate(layoutInflater, container, false)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.buttonCreate.setOnClickListener {
            checkPermission()
        }

        binding.backButton.setOnClickListener { view ->
            Navigation.findNavController(view)
                .navigate(R.id.action_createAnnotationFragment_to_homeFragment)
        }
        return binding.root
    }

    private fun createNotes(it: View?, location: String) {
        val title = binding.editTextTitle.text.toString()
        val content = binding.editTextContent.text.toString()
        val notesDate: CharSequence = DateFormat.format("d MMM yyyy", Date().time)

        val data = Notes(
            null,
            title = title,
            content = content,
            date = notesDate.toString(),
            city = location
        )

        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Nota criada", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createAnnotationFragment_to_homeFragment)
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }

        fusedLocationProviderClient.lastLocation?.addOnSuccessListener {
            it.apply{
                val geocoder = Geocoder(requireContext(), Locale.getDefault())

                val addresses: List<Address> = geocoder.getFromLocation(it.latitude, it.longitude, 1)

                createNotes(binding.root, addresses[0].subAdminArea)
            }
        }
    }
}


