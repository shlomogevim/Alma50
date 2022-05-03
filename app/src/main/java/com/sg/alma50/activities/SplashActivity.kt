package com.sg.alma50.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.auth.FirebaseAuth
import com.sg.alma50.R
import com.sg.alma50.databinding.ActivitySplashBinding
import com.sg.alma50.utilities.BaseActivity
import com.sg.alma50.utilities.FirestoreClass
import com.sg.alma50.utilities.FontFamilies

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setText()
        pauseIt()

    }

    private fun setText() {
        val font= FontFamilies()
        val fontAddress = font.getFamilyFont(103)
        binding.tvAppName.typeface = ResourcesCompat.getFont(this, fontAddress)

        binding.tvAppName.textSize= 22F
        binding.tvAppName.text="זה מה שלימדו אותנו היום בגן ..."
    }
    private fun pauseIt() {

        /*    var currentUserID = FirestoreClass().getCurrentUserID()
            logi("splash 41       currentUserID ===>currentUser=$currentUserID  ")*/


        Handler().postDelayed(
            {
                var currentUserID = FirestoreClass().getCurrentUserID()

                logi("SplashAvtivity 42  \n     currentUserID  ===> $currentUserID  ")

             /*   currentUserID=""
                FirebaseAuth.getInstance().signOut()*/

                        /*  if (currentUserID.isNotEmpty()) {
                      startActivity(Intent(this, MainActivityAppShop::class.java))
                  } else{
                      startActivity(Intent(this, LoginActivity::class.java))
                  }*/

                startActivity(Intent(this, MainActivityAppShop::class.java))

                finish()
            },2
        )
    }
}