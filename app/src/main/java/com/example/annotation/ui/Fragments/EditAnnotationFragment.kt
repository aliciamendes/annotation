package com.example.annotation.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.annotation.Model.Notes
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.databinding.FragmentEditAnnotationBinding
import java.util.*


class EditAnnotationFragment : Fragment() {

    val oldNotes by navArgs<EditAnnotationFragmentArgs>()
    lateinit var binding: FragmentEditAnnotationBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditAnnotationBinding.inflate(layoutInflater, container, false)

        binding.editTextTitle.setText(oldNotes.data.title)
        binding.editTextContent.setText(oldNotes.data.content)

        binding.buttonEdit.setOnClickListener{
            updateNotes(it)
        }
        binding.buttonBackEdit.setOnClickListener{
            Navigation.findNavController(it!!).navigate(R.id.action_editAnnotationFragment_to_homeFragment)
        }


        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTextTitle.text.toString()
        val content = binding.editTextContent.text.toString()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", Date().time)

        val data = Notes(
            oldNotes.data.id,
            title = title,
            content = content,
            date = notesDate.toString())

        // Log.e("@@@@@", "updateNotes: Title: $title  Content: $content" )

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Nota Atualizada com Sucesso!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editAnnotationFragment_to_homeFragment)
    }

}