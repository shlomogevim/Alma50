package com.sg.pager20.adapters

import android.content.Context
import android.content.res.Resources
import androidx.constraintlayout.widget.ConstraintLayout
import com.sg.alma50.modeles.Post
import com.sg.alma50.post_drawing.DrawGeneralPost


class DrawPostCenter(val context: Context) {

   //val util = UtilityPost()

    val draw1Line = DrawGeneralPost()
    val draw2Line = DrawGeneralPost()
    val draw3Line = DrawGeneralPost()
    val draw4Line = DrawGeneralPost()
    val draw5Line = DrawGeneralPost()
    val draw6Line = DrawGeneralPost()
    val draw7Line = DrawGeneralPost()
    val draw8Line = DrawGeneralPost()
    val draw9Line = DrawGeneralPost()
    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


    fun drawPost(post: Post, layout: ConstraintLayout) {
        //util.logi("DrawPostCenter     =========>  /n post=$post")
        when (post.lineNum) {

            1 -> draw1Line.drawPost(context, post, layout)
            2 -> draw2Line.drawPost(context, post, layout)
            3 -> draw3Line.drawPost(context, post, layout)
            4 -> draw4Line.drawPost(context, post, layout)
            5 -> draw5Line.drawPost(context, post, layout)
            6 -> draw6Line.drawPost(context, post, layout)
            7 -> draw7Line.drawPost(context, post, layout)
            8 -> draw8Line.drawPost(context, post, layout)
            9 -> draw9Line.drawPost(context, post, layout)
        }
    }
    fun drawPostFire(post: Post, layout: ConstraintLayout) {
        //util.logi("DrawPostCenter 105     =========>       post.lineNum=${post.lineNum}")

        when (post.lineNum) {

            1 -> draw1Line.drawPostFire(context, post, layout)
            2 -> draw2Line.drawPostFire(context, post, layout)
            3 -> draw3Line.drawPostFire(context, post, layout)
            4 -> draw4Line.drawPostFire(context, post, layout)
            5 -> draw5Line.drawPostFire(context, post, layout)
            6 -> draw6Line.drawPostFire(context, post, layout)
            7 -> draw7Line.drawPostFire(context, post, layout)
            8 -> draw8Line.drawPostFire(context, post, layout)
            9 -> draw9Line.drawPostFire(context, post, layout)
        }
    }
    fun drawPostComment(post: Post, layout: ConstraintLayout) {
        //util.logi("DrawPostCenter 105     =========>       post.lineNum=${post.lineNum}")

        when (post.lineNum) {

            1 -> draw1Line.drawPostFire(context, post, layout)
            2 -> draw2Line.drawPostFire(context, post, layout)
            3 -> draw3Line.drawPostFire(context, post, layout)
            4 -> draw4Line.drawPostFire(context, post, layout)
            5 -> draw5Line.drawPostFire(context, post, layout)
            6 -> draw6Line.drawPostFire(context, post, layout)
            7 -> draw7Line.drawPostFire(context, post, layout)
            8 -> draw8Line.drawPostFire(context, post, layout)
            9 -> draw9Line.drawPostFire(context, post, layout)
        }
    }

/*
    val util = Utility()

    val draw1Line = Draw_total_LinePost()
    val draw2Line = Draw_total_LinePost()
    val draw3Line = Draw_total_LinePost()
    val draw4Line = Draw_total_LinePost()
    val draw5Line = Draw_total_LinePost()
    val draw6Line = Draw_total_LinePost()
    val draw7Line = Draw_total_LinePost()
    val draw8Line = Draw_total_LinePost()
    val draw9Line = Draw_total_LinePost()

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()


    fun drawPost(post: Post, layout: ConstraintLayout) {

        when (post.lineNum) {

            1 -> draw1Line.drawPost(contex, post, layout)
            2 -> draw2Line.drawPost(contex, post, layout)
            3 -> draw3Line.drawPost(contex, post, layout)
            4 -> draw4Line.drawPost(contex, post, layout)
            5 -> draw5Line.drawPost(contex, post, layout)
            6 -> draw6Line.drawPost(contex, post, layout)
            7 -> draw7Line.drawPost(contex, post, layout)
            8 -> draw8Line.drawPost(contex, post, layout)
            9 -> draw9Line.drawPost(contex, post, layout)

            //else-> drawPostTotal(post,layout)
        }

    }*/
}
