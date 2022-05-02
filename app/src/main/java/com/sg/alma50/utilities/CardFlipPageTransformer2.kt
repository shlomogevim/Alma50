package com.sg.alma50.utilities

import android.view.View
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class CardFlipPageTransformer2: ViewPager2.PageTransformer {

   private var scalable = false

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - Math.abs(position)
        page.setCameraDistance(30000f)
        setVisibility(page, position)
        setTranslation(page)
        setSize(page, position, percentage)
        setRotation(page, position, percentage)
    }

    private fun setVisibility(page: View, position: Float) {
        if (position < 0.5 && position > -0.5) {
            page.setVisibility(View.VISIBLE)
        } else {
            page.setVisibility(View.INVISIBLE)
        }
    }

    private fun setTranslation(page: View) {
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            val scroll: Int = viewPager.scrollX - page.getLeft()
            page.setTranslationX(scroll.toFloat())
        } else {
            val scroll: Int = viewPager.scrollY - page.getTop()
            page.setTranslationY(scroll.toFloat())
        }
    }
    /*private fun setTranslation(page: View) {
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            val scroll = viewPager.scrollX - page.left
            page.translationX = scroll.toFloat()
        } else if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
            val scroll = viewPager.scrollY - page.top
            page.translationY = scroll.toFloat()
        }
        page.translationZ = 1f
    }*/

    private fun setSize(page: View, position: Float, percentage: Float) {
        // Do nothing, if its not scalable
       /* if (!scalable) return
        page.setScaleX(if (position != 0f && position != 1f) percentage else 1)
        page.setScaleY(if (position != 0f && position != 1f) percentage else 1)*/
        val num1:Float=if (position != 0f && position != 1f) percentage else 1f
        page.setScaleX(num1)
      //  page.setScaleY(num1)
    }

    private fun setRotation(page: View, position: Float, percentage: Float) {
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (position > 0) {
                page.setRotationY(-180 * (percentage + 1))
            } else {
                page.setRotationY(180 * (percentage + 1))
            }
        } else {
            if (position > 0) {
                page.setRotationX(-180 * (percentage + 1))
            } else {
                page.setRotationX(180 * (percentage + 1))
            }
        }
    }

    private fun requireViewPager(page: View): ViewPager2 {
        val parent: ViewParent = page.getParent()
        val parentParent = parent.parent
        if (parent is RecyclerView && parentParent is ViewPager2) {
            return parentParent
        }
        throw IllegalStateException(
            "Expected the page view to be managed by a ViewPager2 instance."
        )
    }

    fun isScalable(): Boolean {
        return scalable
    }

    fun setScalable(scalable: Boolean) {
        this.scalable = scalable
    }





}