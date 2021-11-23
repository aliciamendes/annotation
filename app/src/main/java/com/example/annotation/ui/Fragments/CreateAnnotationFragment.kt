package com.example.annotation.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.annotation.databinding.FragmentCreateAnnotationBinding


class CreateAnnotationFragment : Fragment() {

    lateinit var binding: FragmentCreateAnnotationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateAnnotationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}