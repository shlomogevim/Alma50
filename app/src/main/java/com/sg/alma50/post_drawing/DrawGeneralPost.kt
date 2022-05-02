package com.sg.alma50.post_drawing

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.RoundedCorner
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sg.alma50.R
import com.sg.alma50.modeles.Post
import com.sg.alma50.utilities.Constants.CONSTANT
import com.sg.alma50.utilities.FontFamilies


class DrawGeneralPost() {
 //val util = UtilityPost()
    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
    val helper = FontFamilies()

    var constraintSet = ConstraintSet()




    fun drawPostFire(context: Context, post: Post, layout: ConstraintLayout) {
        //util.logi("DrawGeneralPost 100      \n     =========>  /n post=$post")
        val textView1 = layout.findViewById<TextView>(R.id.tv1Fire)
        val textView2 = layout.findViewById<TextView>(R.id.tv2Fire)
        val textView3 = layout.findViewById<TextView>(R.id.tv3Fire)
        val textView4 = layout.findViewById<TextView>(R.id.tv4Fire)
        val textView5 = layout.findViewById<TextView>(R.id.tv5Fire)
        val textView6 = layout.findViewById<TextView>(R.id.tv6Fire)
        val textView7 = layout.findViewById<TextView>(R.id.tv7Fire)
        val textView8 = layout.findViewById<TextView>(R.id.tv8Fire)
        val textView9 = layout.findViewById<TextView>(R.id.tv9Fire)
        var textView = layout.findViewById<TextView>(R.id.tv9Fire)
        val imageView = layout.findViewById<ImageView>(R.id.pagerImageFire)
        textView1.text = ""
        textView2.text = ""
        textView3.text = ""
        textView4.text = ""
        textView5.text = ""
        textView6.text = ""
        textView7.text = ""
        textView8.text = ""
        textView9.text = ""

        //util.logi("DrawGeneralPost 110     \n     =========>  /n layout=$layout")

        imageView.load(post.imageUri)
        constraintSet.clone(layout)

        for (index in 1..post.lineNum) {
            textView = when (index) {
                1 -> textView1
                2 -> textView2
                3 -> textView3
                4 -> textView4
                5 -> textView5
                6 -> textView6
                7 -> textView7
                8 -> textView8
                9 -> textView9
                else -> textView1
            }

            createTextView(index, textView, post, context, layout)
            locateTextView(index, textView, post)

        }

        constraintSet.applyTo(layout)



    }


    fun drawPost(context: Context, post: Post, layout: ConstraintLayout) {

    // util.logi("DrawGeneralPost 100      \n     =========>  /n post=$post")


        val textView1 = layout.findViewById<TextView>(R.id.tv1)
        val textView2 = layout.findViewById<TextView>(R.id.tv2)
        val textView3 = layout.findViewById<TextView>(R.id.tv3)
        val textView4 = layout.findViewById<TextView>(R.id.tv4)
        val textView5 = layout.findViewById<TextView>(R.id.tv5)
        val textView6 = layout.findViewById<TextView>(R.id.tv6)
        val textView7 = layout.findViewById<TextView>(R.id.tv7)
        val textView8 = layout.findViewById<TextView>(R.id.tv8)
        val textView9 = layout.findViewById<TextView>(R.id.tv9)
        var textView = layout.findViewById<TextView>(R.id.tv9)
        val imageView = layout.findViewById<ImageView>(R.id.pagerImage)

        textView1.text = ""
        textView2.text = ""
        textView3.text = ""
        textView4.text = ""
        textView5.text = ""
        textView6.text = ""
        textView7.text = ""
        textView8.text = ""
        textView9.text = ""

      //  util.logi("DrawGeneralPost 101      \n     =========>  /n layout=$layout")

        imageView.load(post.imageUri){
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(50f))
        }


        constraintSet.clone(layout)

        for (index in 1..post.lineNum) {
            textView = when (index) {
                1 -> textView1
                2 -> textView2
                3 -> textView3
                4 -> textView4
                5 -> textView5
                6 -> textView6
                7 -> textView7
                8 -> textView8
                9 -> textView9
                else -> textView1
            }

            createTextView(index, textView, post, context, layout)
            locateTextView(index, textView, post)

        }

        constraintSet.applyTo(layout)

    }

    private fun createTextView(
        index: Int, textView: TextView, post: Post, context: Context, layout: ConstraintLayout
    ) {
      val ind = index - 1
        textView.text = post.postText[ind]
       // util.logi("Draw GeneralPost 1012   ${post.postTextColor.joinToString()}")
           if (post.postTextColor[0] == CONSTANT) {
          textView.setTextColor(Color.parseColor(post.postTextColor[1]))
      } else {
          textView.setTextColor(Color.parseColor(post.postTextColor[1]))
      }
    if (post.postTextSize[0] == 0) {
           textView.textSize = post.postTextSize[1].toFloat()
       } else {
           textView.textSize = post.postTextSize[index].toFloat()
       }
       val tra = helper.getTransfo(post.postTransparency)
       val shape = GradientDrawable()
       shape.cornerRadius = post.postRadiuas.toPx().toFloat()
       shape.setColor(Color.parseColor("#$tra${post.postBackground}"))

       for (index in 1..post.postTextColor.size - 1) {
           if (!post.postTextColor[index].contains("#")) {
               post.postTextColor[index] = "#" + post.postTextColor[index]
           }
       }

       textView.background = shape
       val fontAddress = helper.getFamilyFont(post.postFontFamily)

       textView.typeface = ResourcesCompat.getFont(context, fontAddress)
       textView.setPadding(
           post.postPadding[0].toPx(),
           post.postPadding[1].toPx(),
           post.postPadding[2].toPx(),
           post.postPadding[3].toPx()
       )
       textView.gravity = Gravity.CENTER
    }

    private fun locateTextView(
        index: Int,
        textView: TextView,
        post: Post
    ) {
        constraintSet.clear(textView.id, ConstraintSet.TOP)
        constraintSet.clear(textView.id, ConstraintSet.BOTTOM)
        val ind = index - 1

        if (post.postMargin[ind][3] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP, post.postMargin[ind][1].toPx()
            )
        }
        if (post.postMargin[ind][1] == -1) {
            constraintSet.connect(
                textView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM, post.postMargin[ind][3].toPx()
            )
        }
    }
}