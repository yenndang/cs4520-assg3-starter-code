package com.cs4520.assg3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment3.R
import com.cs4520.assignment3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // Declaration of the _binding variable with nullable FragmentHomeBinding type.
    private var _binding: FragmentHomeBinding? = null

    // A non-nullable binding property using Kotlin's backing property technique.
    // It throws an IllegalStateException if _binding is accessed while null, which should never happen.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize _binding with the inflated layout.
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.mvpButton?.setOnClickListener {findNavController().navigate(R.id.action_homeFragment_to_MVPFragment)}
        _binding?.mvvmButton?.setOnClickListener {findNavController().navigate(R.id.action_homeFragment_to_MVVMFragment)}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
