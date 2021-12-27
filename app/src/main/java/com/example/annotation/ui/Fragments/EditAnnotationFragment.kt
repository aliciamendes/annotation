package com.example.annotation.ui.Fragments

import android.app.Activity
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.annotation.Model.Notes
import com.example.annotation.R
import com.example.annotation.ViewModel.NotesViewModel
import com.example.annotation.databinding.FragmentEditAnnotationBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        binding.buttonDeleteNotes.setOnClickListener{
                deleteNotes()
        }

        return binding.root
    }

    private fun deleteNotes() {
        val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        bottomSheet.setContentView(R.layout.dialog_delete_annotation)

        val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes_delete)
        val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no_delete)

        textViewYes?.setOnClickListener {
            viewModel.deleteNotes(oldNotes.data.id!!)
            bottomSheet.dismiss()
            Navigation.findNavController(binding.root).navigate(R.id.action_editAnnotationFragment_to_homeFragment)
            Toast.makeText(requireContext(), "Nota Deletada com Sucesso!", Toast.LENGTH_SHORT).show()
        }

        textViewNo?.setOnClickListener {
            bottomSheet.dismiss()
        }

        bottomSheet.show()
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTextTitle.text.toString()
        val content = binding.editTextContent.text.toString()
        val notesDate: CharSequence = DateFormat.format("d MMM yyyy", Date().time)

        val data = Notes(
            oldNotes.data.id,
            title = title,
            content = content,
            date = notesDate.toString())

        // Log.e("@@@@@", "updateNotes: Titulo: $title  Conteudso: $content" )

        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Nota Atualizada com Sucesso!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editAnnotationFragment_to_homeFragment)
    }

}
