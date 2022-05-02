package com.sg.alma50.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sg.alma50.databinding.ActivityDialogBinding
import com.sg.alma50.utilities.Constants.DIALOG_EXSTRA

class DialogActivity : AppCompatActivity() {
    lateinit var binding: ActivityDialogBinding
    var dialogIndex=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialogIndex=intent.getIntExtra(DIALOG_EXSTRA,1)
        createDialog(dialogIndex)
    }
    fun createDialog( ind: Int) {

        //   logi("Utility 32 createDialoge   =====> ind=$ind      contex=$context")

        val btn1 =binding.btn1Dialog
        val btn2 = binding.btn2Dialog
        val btn3 = binding.btn3Dialog
        val loti = binding.lottieAnimDialog
        val dialogText1 = binding.textDialog1
        val dialogText2 =binding.textDialog2
        val dialogText3 =binding.textDialog3
        val dialogText4 = binding.textDialog4
        btn1.visibility = View.GONE
        btn2.visibility = View.GONE

        //   logi("Utility  createDialoge   =====> ind=$ind")

        val arString:ArrayList<String> =getDialogMessage(ind)

        // m    logi("Utility 52  createDialoge   =====> ind=$ind")
        dialogText1.text =arString[0]
        dialogText2.text =arString[1]
        dialogText3.text =arString[2]
        dialogText4.text =arString[3]
        btn3.text =arString[4]
        loti.setAnimation(arString[5])
        btn1.setOnClickListener { }
        btn2.setOnClickListener { }
        btn3.setOnClickListener {
            finish()
        }
        // dialog.show()

    }

    private fun getDialogMessage(ind: Int): ArrayList<String> {
        var stMessage1 = ""
        var stMessage2 = ""
        var stMessage3 = ""
        var stMessage4 = ""
        var stBackBtn = ""
        var stAnimation = ""
        if (ind ==2) {
            stMessage1 = "אתה כרגע משתתף אנונימי "
            stMessage2 ="ולכן אין לך אישור לכתוב הערות ,"
            stMessage3 =  "אתה צריך קודם  להיכנס ..."
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור להערות"
            stAnimation="right.json"
        }
        if (ind == 1) {
            stMessage1 = "אתה כרגע משתתף אנונימי "
            stMessage2 ="ולכן לא יעזור לך ללחוץ על צלמית השלח ,"
            stMessage3 =  "אתה צריך קודם להיכנס..."
            stMessage4 = " "
            stBackBtn= "לחץ פה כדי לחזור להערות"
            stAnimation="right.json"
        }
        if (ind == 3) {
            stMessage1 = " לא כתבת כלום בהערה ..."
            stMessage2 = "קודם תכתוב משהו,"
            stMessage3 = "ואחר כך לחץ על שלח ..."
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור להערות"
            stAnimation="right.json"
        }
        if (ind == 4) {
            stMessage1 = " לא הכנסת מייל..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך הכניסה"
            stAnimation="right.json"
        }
        if (ind == 5) {
            stMessage1 = " לא הכנסת סיסמה..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך הכניסה"
            stAnimation="right.json"
        }
        if (ind == 6) {
            stMessage1 = " לא הכנסת שם משתמש..."
            stMessage2 = "זה שם שיזהה אותך"
            stMessage3 = "(יכול להיות פקטיבי)"
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 7) {
            stMessage1 = " לא הכנסת מייל..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 8) {
            stMessage1 = " לא הכנסת סיסמה..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 9) {
            stMessage1 = "חביבי, מישהו כבר משתמש במייל הזה,"
            stMessage2 = "נסה להכניס מייל אחר"
            stMessage3 = ""
            stMessage4 = " "
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 10) {
            stMessage1 = "הסיסמה לא תקינה"
            stMessage2 = "צריך להיות לפחות 6 מספרים"
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 11) {
            stMessage1 = "המייל שהכנסת לא תקין ... "
            stMessage2 = "נסה להכניס מייל אחר"
            stMessage3 = "כמובן יכול להיות סתם מייל פקטיבי"
            stMessage4 = " משהוא כמו:        a@bc.com"
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 12) {
            stMessage1 = "השם הזה כבר קיים במערכת ... "
            stMessage2 = "מצא לעצמך שם אחר"
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        if (ind == 13) {
            stMessage1 = "מזל טוב ... "
            stMessage2 = "הצלחת להרשם בהצלחה"
            stMessage3 = "ברוך הבא"
            stMessage4 = ""
            stBackBtn= "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation="right.json"
        }
        return arrayListOf(stMessage1,stMessage2,stMessage3,stMessage4,stBackBtn,stAnimation)
    }
}