import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assg3.mvvm.Event

class CalculatorViewModel : ViewModel() {
    // LiveData for the calculation result
    val resultLiveData = MutableLiveData<String>()

    // Change errorLiveData to use Event wrapper
    val errorLiveData = MutableLiveData<Event<String>>()

    // When posting a new error
    fun postError(message: String) {
        errorLiveData.value = Event(message)
    }

    // Function to perform calculations
    fun calculate(operation: String, number1: String, number2: String) {
        // Check for missing inputs
        if (number1.isBlank() || number2.isBlank()) {
            errorLiveData.value = Event("Input is missing")
            return
        }

        try {
            val num1 = number1.toDouble()
            val num2 = number2.toDouble()

            // Perform the operation
            val result = when (operation) {
                "add" -> num1 + num2
                "subtract" -> num1 - num2
                "multiply" -> num1 * num2
                "divide" -> {
                    if (num2 == 0.0) throw ArithmeticException("Cannot divide by zero")
                    num1 / num2
                }
                else -> throw IllegalArgumentException("Invalid operation")
            }

            // Update the result LiveData
            resultLiveData.value = "Result: $result"
        } catch (e: NumberFormatException) {
            // Handle number format exception for invalid input
            errorLiveData.value = Event("Invalid input")
        } catch (e: Exception) {
            // Handle other exceptions, including arithmetic exceptions
            errorLiveData.value = Event(e.message ?: "Error performing operation")
        }
    }
}
