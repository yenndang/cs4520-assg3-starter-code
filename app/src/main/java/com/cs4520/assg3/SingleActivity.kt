package com.cs4520.assg3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cs4520.assignment3.databinding.ActivityMainBinding

class SingleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    /*
    The onCreate method is a lifecycle callback that runs when the
    activity is being created.
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}