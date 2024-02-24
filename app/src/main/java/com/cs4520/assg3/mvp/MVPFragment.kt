package com.cs4520.assg3.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cs4520.assg3.CalculatorModel
import com.cs4520.assignment3.databinding.FragmentMvpBinding

class MVPFragment : Fragment(), MVPContract.View {

    private var _binding: FragmentMvpBinding? = null
    private val binding get() = _binding!!

    // Instantiate the presenter with CalculatorModel
    private val presenter: MVPContract.Presenter by lazy {
        MVPPresenter(this, CalculatorModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMvpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonAdd.setOnClickListener {
            presenter.onAddClicked(
                binding.number1EditText.text.toString(),
                binding.number2EditText.text.toString()
            )
        }
        binding.buttonSubstract.setOnClickListener {
            presenter.onSubtractClicked(
                binding.number1EditText.text.toString(),
                binding.number2EditText.text.toString()
            )
        }
        binding.buttonMultiply.setOnClickListener {
            presenter.onMultiplyClicked(
                binding.number1EditText.text.toString(),
                binding.number2EditText.text.toString()
            )
        }
        binding.buttonDivide.setOnClickListener {
            presenter.onDivideClicked(
                binding.number1EditText.text.toString(),
                binding.number2EditText.text.toString()
            )
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
