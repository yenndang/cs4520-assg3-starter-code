package com.cs4520.assg3.mvvm

// I did some research and attempted to fix Toast messages reappearing upon orientation change
// (When rotate your device, the Fragment is destroyed and recreated,
// causing the LiveData observers in MVVMFragment to trigger again if the
// LiveData holds a value. Since your error LiveData likely still holds the last error message,
// rotating the device triggers the observer, and thus, the Toast is shown again.)
open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            content
        } else {
            null
        }
    }

    fun peekContent(): T = content
}
