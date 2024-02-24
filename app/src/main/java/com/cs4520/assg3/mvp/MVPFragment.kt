package com.cs4520.assg3.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cs4520.assg3.CalculatorModel
import com.cs4520.assignment3.R
import com.cs4520.assignment3.databinding.FragmentCalculatorBinding

class MVPFragment : Fragment(), MVPContract.View {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    // Instantiate the presenter with CalculatorModel
    private val presenter: MVPContract.Presenter by lazy {
        MVPPresenter(this, CalculatorModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

        // Set the background color for MVP architecture
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.mvpBackground))
    }

    private fun setupClickListeners() {
        // Unified click listener for all operation buttons
        val operationClickListener = View.OnClickListener { view ->
            val operation = when (view.id) {
                R.id.button_add -> "add"
                R.id.button_substract -> "subtract"
                R.id.button_multiply -> "multiply"
                R.id.button_divide -> "divide"
                else -> null
            }
            operation?.let { performOperation(it) }
        }

        // Assign the click listener to each button
        with(binding) {
            buttonAdd.setOnClickListener(operationClickListener)
            buttonSubstract.setOnClickListener(operationClickListener)
            buttonMultiply.setOnClickListener(operationClickListener)
            buttonDivide.setOnClickListener(operationClickListener)
        }
    }

    private fun performOperation(operation: String) {
        // Extract numbers from EditTexts
        val number1 = binding.number1EditText.text.toString()
        val number2 = binding.number2EditText.text.toString()

        // Delegate the operation to the presenter
        when (operation) {
            "add" -> presenter.onAddClicked(number1, number2)
            "subtract" -> presenter.onSubtractClicked(number1, number2)
            "multiply" -> presenter.onMultiplyClicked(number1, number2)
            "divide" -> presenter.onDivideClicked(number1, number2)
        }

        // Clear inputs after operation
        clearInputs()
    }

    private fun clearInputs() {
        with(binding) {
            number1EditText.text.clear()
            number2EditText.text.clear()
        }
    }

    override fun displayResult(result: String) {
        binding.resultTextView.text = result
    }

    override fun displayError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy() // Inform the presenter that the view is being destroyed
        _binding = null
    }
}
