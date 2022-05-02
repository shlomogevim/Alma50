package com.sg.alma50.utilities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.sg.alma50.R
import com.sg.alma50.activities.DialogActivity
import com.sg.alma50.modeles.Comment
import com.sg.alma50.modeles.Post
import com.sg.alma50.modeles.User
import com.sg.alma50.utilities.Constants.COMMEND_TIME_STAMP
import com.sg.alma50.utilities.Constants.COMMENT_ID
import com.sg.alma50.utilities.Constants.COMMENT_LIST
import com.sg.alma50.utilities.Constants.COMMENT_POST_ID
import com.sg.alma50.utilities.Constants.COMMENT_REF
import com.sg.alma50.utilities.Constants.COMMENT_TEXT
import com.sg.alma50.utilities.Constants.COMMENT_USER_ID
import com.sg.alma50.utilities.Constants.COMMENT_USER_NAME
import com.sg.alma50.utilities.Constants.DIALOG_EXSTRA
import com.sg.alma50.utilities.Constants.FIRESTORE_USER_ID
import com.sg.alma50.utilities.Constants.POST_BACKGROUND
import com.sg.alma50.utilities.Constants.POST_FONT_FAMILY
import com.sg.alma50.utilities.Constants.POST_ID
import com.sg.alma50.utilities.Constants.POST_IMAGE_URI
import com.sg.alma50.utilities.Constants.POST_LINE_NUM
import com.sg.alma50.utilities.Constants.POST_MARGIN
import com.sg.alma50.utilities.Constants.POST_NUM
import com.sg.alma50.utilities.Constants.POST_PADDING
import com.sg.alma50.utilities.Constants.POST_RADIUS
import com.sg.alma50.utilities.Constants.POST_REF
import com.sg.alma50.utilities.Constants.POST_TEXT
import com.sg.alma50.utilities.Constants.POST_TEXT_COLOR
import com.sg.alma50.utilities.Constants.POST_TEXT_SIZE
import com.sg.alma50.utilities.Constants.POST_TRANPARECY
import com.sg.alma50.utilities.Constants.USER_BIO
import com.sg.alma50.utilities.Constants.USER_EMAIL
import com.sg.alma50.utilities.Constants.USER_FULLNAME
import com.sg.alma50.utilities.Constants.USER_ID
import com.sg.alma50.utilities.Constants.USER_IMAGE
import com.sg.alma50.utilities.Constants.USER_PASSWORD
import com.sg.alma50.utilities.Constants.USER_TIME
import com.sg.alma50.utilities.Constants.USER_USERNAME


import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.coroutines.suspendCoroutine


class UtilityPost {

    val currentUser = FirebaseAuth.getInstance().currentUser


    fun createDialog(context: Context, ind: Int) {

        val intent= Intent(context, DialogActivity::class.java)
        intent.putExtra(DIALOG_EXSTRA,ind)
        context.startActivity(intent)


        /*      //   logi("Utility 32 createDialoge   =====> ind=$ind      contex=$context")

             val dialog = Dialog(context)
            // logi("Utility 39 createDialoge   =====> ind=$ind")
             dialog.setCancelable(false)
             dialog.setContentView(R.layout.option_menu1)
             val btn1 = dialog.findViewById<Button>(R.id.btn1_dialog)
             val btn2 = dialog.findViewById<Button>(R.id.btn2_dialog)
             val btn3 = dialog.findViewById<Button>(R.id.btn3_dialog)
             val loti = dialog.findViewById<LottieAnimationView>(R.id.lottie_anim_dialog)
             val dialogText1 = dialog.findViewById<TextView>(R.id.text_dialog1)
             val dialogText2 = dialog.findViewById<TextView>(R.id.text_dialog2)
             val dialogText3 = dialog.findViewById<TextView>(R.id.text_dialog3)
             val dialogText4 = dialog.findViewById<TextView>(R.id.text_dialog4)
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
                 dialog.dismiss()
             }
        dialog.show()*/

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
      /*  if (ind == 1) {
            stMessage1 = "אתה כרגע משתתף אנונימי "
            stMessage2 ="ולכן לא יעזור לך ללחוץ על צלמית השלח ,"
            stMessage3 =  "אתה צריך קודם להיכנס..."
            stMessage4 = " "
            stBackBtn= "לחץ פה כדי לחזור להערות"
            stAnimation="right.json"
        }*/
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
/* class UserDataMenager2 {
   var a=0
    lateinit var deffered :Deferred<Int>

    suspend fun getTotalUserCount():Int{
        // this is c  not C     in the coroutine
        coroutineScope {
             deffered=async (Dispatchers.IO){
                delay(1000)
                a=102
              var c=a
                return@async c
            }
        }
        return deffered.await()
    }
}*/

    /*   fun createDialoge(  contex:Context,title:String,body:String) {
           val alertDialog = AlertDialog.Builder(contex, R.style.RoundedCornerDialog).create()
           alertDialog.setTitle(title)
           alertDialog.setMessage(body)

           alertDialog.setButton(
               AlertDialog.BUTTON_NEUTRAL, "לחץ כאן כדי להמשיך ...",
               DialogInterface.OnClickListener {
                       dialog, which -> dialog.dismiss()
                   // finish()
               })
           alertDialog.show()
       }*/
    fun convertToUser(snap: DocumentSnapshot?): User {
        var userName =""
        var fullName = "no fullName"
        var email: String = "no email"
        var profileImage =
            "https://firebasestorage.googleapis.com/v0/b/social55firestore.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=4a02bf76-8cc4-43e7-9750-930176c9c9ee"
        var dio: String = "no dio"
        var uid: String = "no uid"
        userName = snap?.getString(USER_USERNAME).toString()
        fullName = snap?.getString(USER_FULLNAME).toString()
        email = snap?.getString(USER_EMAIL).toString()
        profileImage = snap?.getString(USER_IMAGE).toString()
        dio = snap?.getString(USER_BIO).toString()
        uid = snap?.getString(FIRESTORE_USER_ID).toString()

        val newUser = User(uid=uid, userName = userName, lastName = fullName, email, image = profileImage, moto=dio)
        return newUser
    }
    /* val uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val gender: String = "",
    var dio: String = "",
    val profileCompleted: Int = 0*/


    fun downloadPost1(context: Context, index: Int) {
        // val layout1: ConstraintLayout = (context as Activity).findViewById(R.id.mainLayout1)
        //  val createPost1 = CreatePost1(context, layout1)
        FirebaseFirestore.getInstance().collection(POST_REF).document(index.toString()).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val post = retrivePostFromFirestore(task.result)

                    //  createPost1.drawPost(post)
                }
            }
        /*FirebaseUser*/
    }


    fun createComment(post: Post, commentText: String) {
        val data = HashMap<String, Any>()
        data[COMMENT_ID] = "1"
        data[COMMENT_POST_ID] = post.postNum.toString()
        data[COMMENT_TEXT] = commentText
        data[COMMENT_USER_NAME] = currentUser?.displayName.toString()
        data[COMMENT_USER_ID] = currentUser?.uid.toString()
        data[COMMEND_TIME_STAMP] = FieldValue.serverTimestamp()
        val ref = FirebaseFirestore.getInstance().collection(COMMENT_REF)
            .document(post.postNum.toString())
            .collection(COMMENT_LIST)
        ref.add(data)
            .addOnSuccessListener {
                data[COMMENT_ID] = it.id
                ref.document(it.id).update(data)
            }
    }

    fun deleteComment(comment: Comment) {
        //  logi("Utility 111      comment.postId=${comment.postId}           comment.commntId=${comment.commntId}")
        FirebaseFirestore.getInstance().collection(COMMENT_REF).document(comment.postId)
            .collection(COMMENT_LIST).document(comment.commntId).delete()
    }


    fun retriveCommentFromFirestore(snap: DocumentSnapshot?): Comment {
        val comId = snap?.get(COMMENT_ID).toString()
        val postId = snap?.get(COMMENT_POST_ID).toString()
        val comText = snap?.get(COMMENT_TEXT).toString()
        val comUserName = snap?.get(COMMENT_USER_NAME).toString()
        val comUserId = snap?.get(COMMENT_USER_ID).toString()
        val timestamp = snap?.getTimestamp(COMMEND_TIME_STAMP)
        val newComment = Comment(comId, postId, comText, comUserName, comUserId, timestamp)
        return newComment
    }

    /*data[USER_ID] = uid!!
        data[USER_FULLNAME] = fullName.lowercase(Locale.getDefault())
        data[USER_USERNAME] = userNameString.lowercase(Locale.getDefault())
        data[USER_EMAIL] = email
        data[USER_PASSWORD] = password
        data[USER_BIO] = "It's me man..."
        data[USER_TIME] = FieldValue.serverTimestamp()*/

    fun retrieveUserFromFirestore(snap: DocumentSnapshot?): User {

        val uid = snap?.get(USER_ID).toString()
        val fullName = snap?.get(USER_FULLNAME).toString()
        val name = snap?.get(USER_USERNAME).toString()
        val email = snap?.get(USER_EMAIL).toString()
        val password = snap?.get(USER_PASSWORD).toString()
        val dio = snap?.get(USER_BIO).toString()
        val timestamp = snap?.getTimestamp(USER_TIME)

        val newUser = User(uid,name,fullName,email,"",moto=dio)
        return newUser
    }

    fun retrivePostFromFirestore(snap: DocumentSnapshot?): Post {
        val postId = snap?.get(POST_ID).toString()
        val postNum = snap?.getLong(POST_NUM)!!.toInt()
        val lineNum = snap?.getLong(POST_LINE_NUM)!!.toInt()
        val imageUri = snap?.getString(POST_IMAGE_URI).toString()
        val postText: ArrayList<String> = snap?.get(POST_TEXT) as ArrayList<String>
        val postBackground = snap?.getString(POST_BACKGROUND).toString()
        val postTranparency = snap?.getLong(POST_TRANPARECY)!!.toInt()
        val postTextColor: ArrayList<String> = snap?.get(POST_TEXT_COLOR) as ArrayList<String>
        val postFontFamily = snap?.getLong(POST_FONT_FAMILY)!!.toInt()
        val postRadius = snap?.getLong(POST_RADIUS)!!.toInt()

        val postTextSize1 = snap?.getString(POST_TEXT_SIZE).toString()
        val postTextSize: ArrayList<Int> = convertFromStringArrayToIntArry(postTextSize1)
        val postPadding1 = snap?.getString(POST_PADDING).toString()
        val postPadding: ArrayList<Int> = convertFromStringArrayToIntArry(postPadding1)
        val postMargin1 = snap?.getString(POST_MARGIN).toString()
        val postMargin: ArrayList<ArrayList<Int>> = convertFromStringArrayToIntArry2(postMargin1)

        val newPost1 = Post(
            postId,
            postNum,
            lineNum,
            imageUri,
            postText,
            postMargin,
            postBackground,
            postTranparency,
            postTextSize,
            postPadding,
            postTextColor,
            postFontFamily,
            postRadius
        )
        //logi("Utility 207   post=${newPost1}")
        return newPost1
    }

    suspend fun retrivePostFromFirestore1(snap: DocumentSnapshot?): Post {
        val postId = snap?.get(POST_ID).toString()
        val postNum = snap?.getLong(POST_NUM)!!.toInt()
        val lineNum = snap?.getLong(POST_LINE_NUM)!!.toInt()
        val imageUri = snap?.getString(POST_IMAGE_URI).toString()
        val postText: ArrayList<String> = snap?.get(POST_TEXT) as ArrayList<String>
        val postBackground = snap?.getString(POST_BACKGROUND).toString()
        val postTranparency = snap?.getLong(POST_TRANPARECY)!!.toInt()
        val postTextColor: ArrayList<String> = snap?.get(POST_TEXT_COLOR) as ArrayList<String>
        val postFontFamily = snap?.getLong(POST_FONT_FAMILY)!!.toInt()
        val postRadius = snap?.getLong(POST_RADIUS)!!.toInt()

        val postTextSize1 = snap?.getString(POST_TEXT_SIZE).toString()
        val postTextSize: ArrayList<Int> = convertFromStringArrayToIntArry(postTextSize1)
        val postPadding1 = snap?.getString(POST_PADDING).toString()
        val postPadding: ArrayList<Int> = convertFromStringArrayToIntArry(postPadding1)
        val postMargin1 = snap?.getString(POST_MARGIN).toString()
        val postMargin: ArrayList<ArrayList<Int>> = convertFromStringArrayToIntArry2(postMargin1)

        val newPost1 = Post(
            postId,
            postNum,
            lineNum,
            imageUri,
            postText,
            postMargin,
            postBackground,
            postTranparency,
            postTextSize,
            postPadding,
            postTextColor,
            postFontFamily,
            postRadius
        )
        //logi("Utility 207   post=${newPost1}")
        return newPost1
    }

    private fun convertFromStringArrayToIntArry(str: String): ArrayList<Int> {
        var newAr = ArrayList<Int>()
        return littleHelper(str, newAr)
    }

    private fun littleHelper(str: String, arr: ArrayList<Int>): ArrayList<Int> {
        val str = str.split(",")
        for (index in 0 until str.size) {
            arr.add(str[index].trim().toInt())
        }
        return arr
    }

    private fun convertFromStringArrayToIntArry2(str: String): ArrayList<ArrayList<Int>> {
        var newAr = ArrayList<ArrayList<Int>>()

        return littleHelperForMargin(str, newAr)
    }

    private fun littleHelperForMargin(
        str: String,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        val str1 = str.replace("]", "").replace("[", "")

        var arStr = str1.split(",")
        //  logi("Utilities 250 arStr=${arStr}")
        val ind = arStr.size.div(4)
        //logi("Utility300  ind=${ind}")

        when (ind) {
            1 -> helper10(arStr, bigArray)
            2 -> helper20(arStr, bigArray)
            3 -> helper30(arStr, bigArray)
            4 -> helper40(arStr, bigArray)
            5 -> helper50(arStr, bigArray)
            6 -> helper60(arStr, bigArray)
            7 -> helper70(arStr, bigArray)
            8 -> helper80(arStr, bigArray)
            9 -> helper90(arStr, bigArray)


        }

        return bigArray
    }

    private fun helper10(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        return bigArray
    }

    private fun helper20(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        return bigArray
    }

    private fun helper30(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        for (index in 8..11) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(2, ar3)
        }
        return bigArray
    }

    private fun helper40(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        for (index in 8..11) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(2, ar3)
        }
        for (index in 12..15) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(3, ar4)
        }
        return bigArray
    }

    private fun helper50(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        return bigArray
    }

    private fun helper60(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()


        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        return bigArray
    }

    private fun helper70(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()
        var ar6 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        for (index in 24..27) {
            ar6.add(arStr[index].trim().toInt())
            bigArray.add(6, ar6)
        }
        return bigArray
    }

    private fun helper80(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()
        var ar6 = arrayListOf<Int>()
        var ar7 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        for (index in 24..27) {
            ar6.add(arStr[index].trim().toInt())
            bigArray.add(6, ar6)
        }
        for (index in 28..31) {
            ar7.add(arStr[index].trim().toInt())
            bigArray.add(7, ar7)
        }
        return bigArray
    }

    private fun helper90(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()
        var ar6 = arrayListOf<Int>()
        var ar7 = arrayListOf<Int>()
        var ar8 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        for (index in 24..27) {
            ar6.add(arStr[index].trim().toInt())
            bigArray.add(6, ar6)
        }
        for (index in 28..31) {
            ar7.add(arStr[index].trim().toInt())
            bigArray.add(7, ar7)
        }
        for (index in 32..35) {
            ar8.add(arStr[index].trim().toInt())
            bigArray.add(8, ar8)
        }
        return bigArray
    }


    fun sendPostToStringFirestore(post: Post) {
        val data = HashMap<String, Any>()
        with(post) {
            data[POST_ID] = 1
            data[POST_NUM] = postNum
            data[POST_LINE_NUM] = lineNum
            data[POST_IMAGE_URI] = imageUri
            data[POST_TEXT] = postText
            data[POST_MARGIN] = postMargin.joinToString()
            data[POST_BACKGROUND] = postBackground
            data[POST_TRANPARECY] = postTransparency
            data[POST_TEXT_SIZE] = postTextSize.joinToString()
            data[POST_PADDING] = postPadding.joinToString()
            data[POST_TEXT_COLOR] = postTextColor
            data[POST_FONT_FAMILY] = postFontFamily
            data[POST_RADIUS] = postRadiuas
        }
        FirebaseFirestore.getInstance().collection(POST_REF).document(post.postNum.toString())
            .set(data)
    }

    fun toasti(context: Context, str: String) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show()
    }

    fun logi(
        element1: String,
        element2: String = "",
        element3: String = "",
        element4: String = ""
    ) {
        if (element1 != "" && element2 == "" && element3 == "" && element4 == "") {
            Log.d("gg", "${element1}")
        }
        if (element1 != "" && element2 != "" && element3 == "" && element4 == "") {
            Log.d("gg", "${element1} ,${element2}")
        }
        if (element1 != "" && element2 != "" && element3 != "" && element4 == "") {
            Log.d("gg", "${element1} ,${element2} ,${element3}")
        }
        if (element1 != "" && element2 != "" && element3 != "" && element4 != "") {
            Log.d("gg", "${element1} ,${element2} ${element3},${element4}")
        }
    }




}
