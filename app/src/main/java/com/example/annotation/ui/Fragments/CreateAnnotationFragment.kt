package com.example.annotation.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.annotation.Model.Notes
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.databinding.FragmentCreateAnnotationBinding
import java.util.*


class CreateAnnotationFragment : Fragment() {

    lateinit var binding: FragmentCreateAnnotationBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAnnotationBinding.inflate(layoutInflater, container, false)

        binding.buttonCreate.setOnClickListener {
            createNotes(it)
        }
        binding.backButton.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_createAnnotationFragment_to_homeFragment)
        }
        return binding.root
    }

    private fun createNotes(it: View?){
        val title = binding.editTextTitle.text.toString()
        val content = binding.editTextContent.text.toString()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", Date().time)

        val data = Notes(null,
            title = title,
            content = content,
            date = notesDate.toString())

        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Nota Criada com Sucesso!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createAnnotationFragment_to_homeFragment)

    }


}