package com.sg.alma50.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma50.R
import com.sg.alma50.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun userProfileUpdateSuccess() {

    }

    fun hideProgressDialog() {

    }

    fun imageUploadSuccess(toString: String) {

    }
}