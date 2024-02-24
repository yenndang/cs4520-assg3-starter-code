package com.cs4520.assg3.mvvm

import CalculatorViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cs4520.assignment3.R
import com.cs4520.assignment3.databinding.FragmentCalculatorBinding

class MVVMFragment : Fragment() {

    // Variable for view binding to access the layout's views.
    private var _binding: FragmentCalculatorBinding? = null
    // Non-nullable version of the binding variable
    private val binding get() = _binding!!

    // ViewModel initialization using the 'by viewModels()' Kotlin property delegate.
    // This ensures the ViewModel is associated with the fragment's lifecycle.
    private val viewModel: CalculatorViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using view binding and return the root view.
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Once the view is created, setup LiveData observers and click listeners for the operation buttons
        setupObservers()
        setupOperationButtonListeners()

        // Set the background color for MVP architecture
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.mvvmBackground))

        viewModel.errorLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { error ->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        // Observe changes to the result LiveData in the ViewModel.
        viewModel.resultLiveData.observe(viewLifecycleOwner) { result ->
            binding.resultTextView.text = result
        }

        // Observe changes to the error LiveData in the ViewModel.
        viewModel.errorLiveData.observe(viewLifecycleOwner) { event ->
            // Unwrap the Event to get the actual error message
            event.getContentIfNotHandled()?.let { error ->
                // Now 'error' is the unwrapped String
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupOperationButtonListeners() {
        // Map each operation button to its corresponding operation string.
        val operationButtons = mapOf(
            binding.buttonAdd to "add",
            binding.buttonSubstract to "subtract",
            binding.buttonMultiply to "multiply",
            binding.buttonDivide to "divide"
        )

        // Iterate over the map and set a click listener for each button.
        // When clicked, performCalculation is called with the appropriate operation.
        operationButtons.forEach { (button, operation) ->
            button.setOnClickListener {
                performCalculation(operation)
            }
        }
    }

    private fun performCalculation(operation: String) {
        // Call the calculate function in the ViewModel with the operation and input values.
        // Then, clear the input fields
        viewModel.calculate(
            operation,
            binding.number1EditText.text.toString(),
            binding.number2EditText.text.toString()
        )
        clearInputs()
    }

    private fun clearInputs() {
        // Clear the text fields for both number inputs.
        binding.number1EditText.text.clear()
        binding.number2EditText.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the view binding when the view is destroyed
        _binding = null
    }
}

