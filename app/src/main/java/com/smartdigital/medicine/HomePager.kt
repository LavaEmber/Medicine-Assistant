package com.smartdigital.medicine

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

//custom ViewPager that works with ViewGroups instead of fragments
class HomePager(context: Context?, attrs: AttributeSet?) : ViewPager(context!!, attrs)
{
    private var initialXValue = 0f
    private var diff = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isSwipeAllowed(event)) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isSwipeAllowed(event)) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    private fun isSwipeAllowed(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN)
        {
            initialXValue = event.x
            return true
        }
        if (event.action == MotionEvent.ACTION_MOVE)
        {
            diff = event.x - initialXValue
            if (diff > 0 && MainActivity.homePager.currentItem == 0)
            // swipe from left to right detected
                return false
            else if (diff < 0 && MainActivity.homePager.currentItem == 1)
            // swipe from right to left detected
                return false
        }
        return true
    }
}

//custom pager adapter for the custom Viewpager above
class SectionsPagerAdapter : PagerAdapter()
{
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var resId = 0
        when (position) {
            0 -> resId = R.id.search_page
            1 -> resId = R.id.setup_page
        }
        return container.findViewById(resId)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun getCount(): Int {
        return 2
    }
}