package com.sg.alma50.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sg.alma50.R
import com.sg.alma50.adapters.CommentAdapter
import com.sg.alma50.modeles.User
import com.sg.alma50.databinding.ActivityPostDetailesBinding
import com.sg.alma50.interfaces.CommentsOptionClickListener
import com.sg.alma50.modeles.Comment
import com.sg.alma50.modeles.Post
import com.sg.alma50.utilities.BaseActivity
import com.sg.alma50.utilities.Constants.COMMEND_TIME_STAMP
import com.sg.alma50.utilities.Constants.COMMENT_ID
import com.sg.alma50.utilities.Constants.COMMENT_LIST
import com.sg.alma50.utilities.Constants.COMMENT_POST_ID
import com.sg.alma50.utilities.Constants.COMMENT_REF
import com.sg.alma50.utilities.Constants.COMMENT_TEXT
import com.sg.alma50.utilities.Constants.POST_EXSTRA
import com.sg.alma50.utilities.Constants.POST_REF
import com.sg.alma50.utilities.Constants.USER_EXTRA
import com.sg.alma50.utilities.FirestoreClass
import com.sg.alma50.utilities.UtilityPost
import java.util.ArrayList

//class PostDetailesActivity :BaseActivity(){
class PostDetailesActivity :BaseActivity(), CommentsOptionClickListener {
    lateinit var binding: ActivityPostDetailesBinding
    private var currentUser:User?= null
    var util = UtilityPost()
    var textViewArray = ArrayList<TextView>()
    lateinit var commentsRV: RecyclerView
    lateinit var commentAdapter: CommentAdapter
    val comments = ArrayList<Comment>()
    lateinit var currentPost: Post
    var message=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)





        if (intent.hasExtra(POST_EXSTRA)) {
            currentPost = intent.getParcelableExtra(POST_EXSTRA)!!

        }

       // logi("PostDetailActivity  58          currentPost===>> $currentPost  /n")
         logi("PostDetailActivity  59")
      drawHeadline()
      create_commentsRv()
      operateButtoms()
       retriveComments()

       createCommant()
   }



     override fun onStart() {
         super.onStart()
     FirestoreClass().getUserDetails(this)
     }
    fun getUserNameSetting(user: User) {
        currentUser=user
        if (currentUser==null){
            binding.nameCurrentUserName.setText("אנונימי")
        }else{
            binding.nameCurrentUserName.setText("${currentUser!!.userName}")
        }
    }

     private fun operateButtoms() {

        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.profileBtn.setOnClickListener {
            //  logi("PostDetaileActivity  92   =====> /n  currentPost=$currentPost ")
            val intent=Intent(this, SettingActivity::class.java)
            intent.putExtra(USER_EXTRA,currentUser)
            startActivity(intent)
        }
    }


    private fun createCommant(){


        /*val textWatcher=object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                logi("PostDetailsActivuty  102                            currentUser=$currentUser")
                if (currentUser==null){
                    hideKeyboard()
                    message="כדי לכתוב הערות אתה צריך קודם להכנס ..."
                    showErrorSnackBar(message, true)
                     }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
            override fun afterTextChanged(p0: Editable?) {  }
        }*/




        binding.profileImageComment.setOnClickListener {
            press_on_comment_icon()
        }

        binding.postCommentText.setOnClickListener {
            press_on_text_comment()

           /* if (currentUser == null) {
                hideKeyboard()
                message="צריך להכנס כדי לכתוב הערה"
                showErrorSnackBar(message,true )
            }else{
                val textC= binding.postCommentText
                textC.addTextChangedListener(textWatcher)


                    addComment()
            }*/

        }
    }

    private fun press_on_text_comment() {
        if (currentUser == null) {
            hideKeyboard()
            message="צריך להכנס כדי לכתוב הערה"
            showErrorSnackBar(message,true )
        }else {

          /*  val textWatcher1=object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    logi("PostDetailsActivuty  102                            currentUser=$currentUser")
                    if (currentUser==null){
                        hideKeyboard()
                        message="כדי לכתוב הערות אתה צריך קודם להכנס ..."
                        showErrorSnackBar(message, true)
                    }
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }
                override fun afterTextChanged(p0: Editable?) {  }
            }

            val textC= binding.postCommentText
            textC.addTextChangedListener(textWatcher1)*/


        }

    }

    private fun press_on_comment_icon() {
        if (currentUser == null) {
            hideKeyboard()
            message="צריך להכנס כדי לכתוב הערה"
            showErrorSnackBar(message,true )
        }else{
            val commentText = binding.postCommentText.text.toString()
            if (commentText == "") {
                message=" היי , קודם תכתוב משהו בהערה ואחר כך תלחץ ..."
                showErrorSnackBar(message, true)
            }else{
                sendComment()
            }
        }
    }

    private fun sendComment() {
        binding.postCommentText.text.clear()
        hideKeyboard()
        FirebaseFirestore.getInstance().collection(POST_REF)
            .document(currentPost.postNum.toString())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val post = util.retrivePostFromFirestore(task.result)
                    val commentText = binding.postCommentText.text.toString()
                    currentUser?.let { util.createComment(post, commentText, it) }
                }
            }
    }


    private fun addComment() {
        //util.logi("PostDetails  112                               currentUser=$currentUser")
        if (currentUser == null) {
            //   util.logi("PostDetails  113                               currentUser=$currentUser")
            hideKeyboard()
            message="צריך להכנס כדי לכתוב הערה"
            showErrorSnackBar(message,true )
        } else {
           // util.logi("PostDetails  114                               currentUser=$currentUser")
            val commentText = binding.postCommentText.text.toString()
            if (commentText == "") {
                message=" היי , קודם תכתוב משהו בהערה ואחר כך תלחץ ..."
                showErrorSnackBar(message, true)
            } else {
                binding.postCommentText.text.clear()
                hideKeyboard()
                FirebaseFirestore.getInstance().collection(POST_REF)
                    .document(currentPost.postNum.toString())
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val post = util.retrivePostFromFirestore(task.result)
                            util.createComment(post, commentText, currentUser!!)
                        }
                    }
            }
        }
    }


    private fun drawHeadline() {
        val num = currentPost.postNum
        val st = "   פוסט מספר: " + "$num   "
        binding.postNumber.text = st
        // logi("PostDetailsActivity  111  post=$currentPost    \n post.postText.size= ${currentPost.postText.size}")

        drawPostText()


        /*    binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]*/


        /*  for (ind in 0 until currentPost.postText.size) {
             logi("PostDetailsActivity  144  ind=$ind     \n")
            textViewArray[ind].visibility = View.VISIBLE
               textViewArray[ind].text = currentPost.postText[ind]
          }*/
    }
    private fun retriveComments() {
        //  logi(" PostDetail 124")
        FirebaseFirestore.getInstance().collection(COMMENT_REF).document(currentPost.postNum.toString())
            .collection(COMMENT_LIST)
            .orderBy(COMMEND_TIME_STAMP, Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    comments.clear()
                    for (doc in value.documents) {
                        val comment = util.retriveCommentFromFirestore(doc)
                        comments.add(comment)
                    }
                    //  logi("PostDetailsActivity 135        comments.size=${comments.size} ")
                    commentAdapter.notifyDataSetChanged()
                }
            }
    }

    private fun create_commentsRv() {
        commentsRV = binding.rvPost
        commentAdapter = CommentAdapter(comments, this)
        val layoutManger = LinearLayoutManager(this)
        layoutManger.reverseLayout = true
        commentsRV.layoutManager = layoutManger
        commentsRV.adapter = commentAdapter
    }




    private fun hideKeyboard() {
        val inputeManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputeManager.isAcceptingText) {
            inputeManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    override fun optionMenuClicked(comment: Comment) {

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.option_menu, null)
        val deleteBtn = dialogView.findViewById<Button>(R.id.optionDelelBtn)
        val editBtn = dialogView.findViewById<Button>(R.id.optionEditBtn)
        builder.setView(dialogView)
            .setNegativeButton("Cancel") { _, _ -> }
        val ad = builder.show()
        deleteBtn.setOnClickListener {
            util.deleteComment(comment)
            finish()
        }
        editBtn.setOnClickListener {
            val intent = Intent(this, UpdateCommentActivity::class.java)
            intent.putExtra(COMMENT_POST_ID, comment.postId)
            intent.putExtra(COMMENT_ID, comment.commntId)
            intent.putExtra(COMMENT_TEXT, comment.text)
            startActivity(intent)
            finish()
        }
    }

    private fun createTextViewArray() {
        with(binding) {
            textViewArray = arrayListOf(
                tvPost1,
                tvPost2,
                tvPost3,
                tvPost4,
                tvPost5,
                tvPost6,
                tvPost7,
                tvPost8,
                tvPost9
            )
        }

    }

    private fun drawPostText() {
        if (currentPost.postText.size==1){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
        }
        if (currentPost.postText.size==2){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
        }
        if (currentPost.postText.size==3){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
        }
        if (currentPost.postText.size==4){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
        }
        if (currentPost.postText.size==5){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost5.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
            binding.tvPost5.text=currentPost.postText[4]
        }
        if (currentPost.postText.size==6){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost5.visibility=View.VISIBLE
            binding.tvPost6.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
            binding.tvPost5.text=currentPost.postText[4]
            binding.tvPost6.text=currentPost.postText[5]
        }
        if (currentPost.postText.size==7){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost5.visibility=View.VISIBLE
            binding.tvPost6.visibility=View.VISIBLE
            binding.tvPost7.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
            binding.tvPost5.text=currentPost.postText[4]
            binding.tvPost6.text=currentPost.postText[5]
            binding.tvPost7.text=currentPost.postText[6]
        }
        if (currentPost.postText.size==8){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost5.visibility=View.VISIBLE
            binding.tvPost6.visibility=View.VISIBLE
            binding.tvPost7.visibility=View.VISIBLE
            binding.tvPost8.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
            binding.tvPost5.text=currentPost.postText[4]
            binding.tvPost6.text=currentPost.postText[5]
            binding.tvPost7.text=currentPost.postText[6]
            binding.tvPost8.text=currentPost.postText[7]
        }
        if (currentPost.postText.size==9){
            binding.tvPost1.visibility=View.VISIBLE
            binding.tvPost2.visibility=View.VISIBLE
            binding.tvPost3.visibility=View.VISIBLE
            binding.tvPost4.visibility=View.VISIBLE
            binding.tvPost5.visibility=View.VISIBLE
            binding.tvPost6.visibility=View.VISIBLE
            binding.tvPost7.visibility=View.VISIBLE
            binding.tvPost8.visibility=View.VISIBLE
            binding.tvPost9.visibility=View.VISIBLE
            binding.tvPost1.text=currentPost.postText[0]
            binding.tvPost2.text=currentPost.postText[1]
            binding.tvPost3.text=currentPost.postText[2]
            binding.tvPost4.text=currentPost.postText[3]
            binding.tvPost5.text=currentPost.postText[4]
            binding.tvPost6.text=currentPost.postText[5]
            binding.tvPost7.text=currentPost.postText[6]
            binding.tvPost8.text=currentPost.postText[7]
            binding.tvPost9.text=currentPost.postText[8]
        }
    }



}