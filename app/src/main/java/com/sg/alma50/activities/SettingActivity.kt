package com.sg.alma50.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.sg.alma50.R
import com.sg.alma50.modeles.User
import com.sg.alma50.databinding.ActivitySettingBinding
import com.sg.alma50.utilities.BaseActivity
import com.sg.alma50.utilities.Constants.USER_EXTRA
import com.sg.alma50.utilities.FirestoreClass
import com.sg.alma50.utilities.GlideLoader

class SettingActivity : BaseActivity(),View.OnClickListener{
    lateinit var binding: ActivitySettingBinding
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  currentUser = intent.getParcelableExtra(Constants.USER_EXTRA)!!
        // logi("SettingActivity   26 =======>  /n $currentUser  ")

        binding.tvEditProfile.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }
    override fun onResume() {        // when we load app
        super.onResume()
        FirestoreClass().getUserDetails(this)
    }
    fun getUserNameSetting(user: User) {
        currentUser=user
        getUserDetails()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tv_editProfile -> {
                    val intent = Intent(this, UserProfileActivity::class.java)
                    intent.putExtra(USER_EXTRA, currentUser)  //need update profile so "onResume"
                    startActivity(intent)
                }
                R.id.btn_logout -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK  // clear all layers in the stuck
                    startActivity(intent)
                    finish()
                }
            }
        }
    }


    private fun getUserDetails() {
//        showProgressDialog(resources.getString(R.string.please_wait))
//        // Call the function of Firestore class to get the user details from firestore which is already created.
//        FirestoreClass().getUserDetails(this)
        GlideLoader(this).loadUserPicture(currentUser.image, binding.ivUserPhoto)

        binding.tvUserName.text = currentUser.userName
        binding.tvLastName.text = currentUser.lastName
        binding.tvGender.text =currentUser.gender
        binding.tvMail.text = currentUser.email
        binding.tvMoto.text = currentUser.moto
        logi("SettingActivity 67  ==============>         \n currentUser= $currentUser")
    }


    /* fun userDetailsSuccess(user: User) {
         currentUser = user
         hideProgressDialog()
 //        logi("setting 69 user=$user")

         GlideLoader(this).loadUserPicture(user.image, binding.ivUserPhoto)

         binding.tvUserName.text = user.userName
         binding.tvLastName.text = user.lastName
         binding.tvGender.text =user.gender
         binding.tvMail.text = user.email
          }*/
}

/**
 * A function for actionBar Setup.
 */
/*private fun setupActionBar() {

    setSupportActionBar(toolbar_settings_activity)

    val actionBar = supportActionBar
    if (actionBar != null) {
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
    }

    toolbar_settings_activity.setNavigationOnClickListener { onBackPressed() }
}*/