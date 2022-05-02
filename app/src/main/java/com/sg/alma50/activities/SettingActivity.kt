package com.sg.alma50.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sg.alma50.modeles.User
import com.sg.alma50.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getUserNameSetting(user: User) {

    }

    fun hideProgressDialog() {

    }
}