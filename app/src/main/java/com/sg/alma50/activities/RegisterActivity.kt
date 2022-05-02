package com.sg.alma50.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma50.R
import com.sg.alma50.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun userRegistrationSuccess() {

    }

    fun hideProgressDialog() {

    }
}