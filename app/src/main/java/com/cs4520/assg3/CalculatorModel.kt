package com.cs4520.assg3

class CalculatorModel : ICalculatorModel {
    override fun add(number1: Double, number2: Double): Double = number1 + number2
    override fun subtract(number1: Double, number2: Double): Double = number1 - number2
    override fun multiply(number1: Double, number2: Double): Double = number1 * number2
    override fun divide(number1: Double, number2: Double): Double {
        if (number2 == 0.0) throw ArithmeticException("Cannot divide by zero")
        return number1 / number2
    }
}
