package com.example.annotation.ui.Fragments

import android.location.LocationRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import android.text.format.DateFormat
import android.util.Log
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.databinding.FragmentHomeBinding
import com.example.annotation.ui.Adapter.NotesAdapter
import java.util.*

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var greetings: String
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        greetings = getTimeCourse()
        binding.nameUser.setText(greetings)

        viewModel.getNotes().observe(
            viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            })

        binding.buttonAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createAnnotationFragment)
        }

        return binding.root
    }

    private fun getTimeCourse(): String {

        var timeNowInt: Int = DateFormat.format("H", Date().time).toString().toInt()
        var newGreetings: String
        if (timeNowInt in 0..12) {
            newGreetings = "Olá, bom dia"
        } else if (timeNowInt in 13..18) {
            newGreetings = "Olá, boa tarde"
        } else {
            newGreetings = "Olá, boa noite"
        }
        return newGreetings
    }

}
