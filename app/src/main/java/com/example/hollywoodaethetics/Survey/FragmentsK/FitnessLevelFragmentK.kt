package com.example.hollywoodaethetics.Survey.FragmentsK

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.hollywoodaethetics.R

/**
 * A simple [Fragment] subclass.
 */
class FitnessLevelFragmentK : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness_level, container, false)
    }

}
