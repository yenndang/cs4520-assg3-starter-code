package com.cs4520.assg3.mvp

import com.cs4520.assg3.ICalculatorModel

class MVPPresenter(
    private var view: MVPContract.View?,
    private val model: ICalculatorModel // Injecting the model interface
) : MVPContract.Presenter {

    override fun onAddClicked(number1: String, number2: String) {
        computeResult(number1, number2) { n1, n2 -> model.add(n1, n2) }
    }

    override fun onSubtractClicked(number1: String, number2: String) {
        computeResult(number1, number2) { n1, n2 -> model.subtract(n1, n2) }
    }

    override fun onMultiplyClicked(number1: String, number2: String) {
        computeResult(number1, number2) { n1, n2 -> model.multiply(n1, n2) }
    }

    override fun onDivideClicked(number1: String, number2: String) {
        computeResult(number1, number2) { n1, n2 ->
            if (n2 == 0.0) throw ArithmeticException("Cannot divide by zero")
            model.divide(n1, n2)
        }
    }

    override fun onDestroy() {
        view = null
    }

    private fun computeResult(number1: String, number2: String, operation: (Double, Double) -> Double) {
        if (number1.isBlank() || number2.isBlank()) {
            view?.displayError("Input is missing")
            return
        }
        try {
            val num1 = number1.toDouble()
            val num2 = number2.toDouble()
            val result = operation(num1, num2)
            view?.displayResult(result.toString())
        } catch (e: NumberFormatException) {
            view?.displayError("Invalid input")
        } catch (e: ArithmeticException) {
            view?.displayError(e.message ?: "Error performing operation")
        }
    }
}
