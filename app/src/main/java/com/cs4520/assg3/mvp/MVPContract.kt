package com.cs4520.assg3.mvp

interface MVPContract {

    interface View {
        fun displayResult(result: String)
        fun displayError(message: String)
    }

    interface Presenter {
        fun onAddClicked(number1: String, number2: String)
        fun onSubtractClicked(number1: String, number2: String)
        fun onMultiplyClicked(number1: String, number2: String)
        fun onDivideClicked(number1: String, number2: String)
        fun onDestroy()
    }
}
