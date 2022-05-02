package com.sg.alma50.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.sg.alma50.R
import com.sg.alma50.interfaces.CommentsOptionClickListener
import com.sg.alma50.modeles.Comment
import com.sg.alma50.modeles.User
import com.sg.alma50.utilities.Constants.USER_REF
import com.sg.alma50.utilities.UtilityPost


import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class CommentAdapter(
    val comments: ArrayList<Comment>,
    val commentOptionListener: CommentsOptionClickListener
) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private lateinit var context: Context
    val util = UtilityPost()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindComment(comments[position])
    }

    override fun getItemCount() = comments.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageProfile = itemView.findViewById<CircleImageView>(R.id.user_profile_image_comment)
        val userNameTV = itemView.findViewById<TextView>(R.id.user_name_comment)
        var commentTv = itemView.findViewById<TextView>(R.id.comment_comment)
        var commentCardView = itemView.findViewById<CardView>(R.id.commentCardView)
        val commentTimeStamp = itemView.findViewById<TextView>(R.id.commentTimestampTv)

        fun bindComment(comment: Comment) {
            setCurrentUserImage(imageProfile, comment)
            userNameTV.text = comment.userName
            commentTv.text = comment.text



            val formatTime = SimpleDateFormat("MM/dd/yyyy hh:mm")
            if (comment.timestamp!=null) {
                val time = formatTime.format(comment.timestamp.toDate())
                commentTimeStamp.text = time.toString()
            }

            commentCardView.setOnClickListener {
                commentOptionListener.optionMenuClicked(comment)
            }
        }

        private fun setCurrentUserImage(imageProfile: CircleImageView?, comment: Comment) {
            var uid = comment.userId
            FirebaseFirestore.getInstance().collection(USER_REF).document(uid).get()
                .addOnSuccessListener {
                    val user: User = util.convertToUser(it)
                    // util.logi("CommentAdapter use=$user")
                    if (user.image.isNotEmpty()) {
                        Picasso.get().load(user.image).placeholder(R.drawable.profile)
                            .into(imageProfile)
                    }

                }

        }

    }
}

/* FirebaseFirestore.getInstance().collection(COMMENT_REF).document(currentPostNum.toString())
            .collection(COMMENT_LIST).addSnapshotListener { value, error ->
                if (value != null) {
                    for (doc in value.documents){
                           val comment=util.retriveCommentFromFirestore(doc)
                        comments.add(comment)
                        commentAdapter.notifyDataSetChanged()
                    }
                }
            }*/
