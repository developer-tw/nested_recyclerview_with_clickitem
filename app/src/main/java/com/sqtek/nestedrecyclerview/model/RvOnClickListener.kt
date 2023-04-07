package com.sqtek.nestedrecyclerview.model

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RvOnClickListener {
    var childPosition:Int = -1
    var parentPosition: Int = -1
    var view: View? = null

    // The user calls this function if any view is Clicked(in parent or child RecyclerView).
    // If it is called  from the Parent RecyclerView adapter, then
    fun onNestedRvClick(typeOfAdapter: String, position: Int, courseUrl: String) {
        Log.d("ETHAN=>","onNestedRvClick(), type=${typeOfAdapter}, pos=$position, url=${courseUrl}")
        if (typeOfAdapter == "Parent") {
            //view = clickedView
            parentPosition = position
            this.onResult(parentPosition, childPosition, courseUrl)
            childPosition = -1
            parentPosition = -1
            view = null
        } else if (typeOfAdapter == "Child") {
            //view = clickedView
            Log.d("ETHAN=>","onNestedRvClick()-child")
            childPosition = position
            this.onResult(parentPosition, childPosition, courseUrl)
            childPosition = -1
            parentPosition = -1
            view = null
        }
    }
    // This function is called from the onItemClick() function in the Activity/Fragment that uses
    // an instance of this class(ClickReporter). It gets called irrespective if the user clicked on
    // a view in Parent RecyclerView or Child RecyclerView.
    // But we only need the information from the call to this function if the
    // user clicked on a View in the Child RecyclerView as it tells us which position in the Parent
    // RecyclerView was clicked.
    fun onNestedRvItemClick(position: Int) {
        Log.d("ETHAN=>","onNestedRvItemClick(), pos=$position")
        parentPosition = position
    }

    // This is the function the user has to over ride in their Activity/Fragment where they will
    // be using ClickReporter. It lets them know which position in the Parent RecyclerView was clicked,
    // position in Child RecyclerView and specific view in the layout that was clicked.
    // If the clicked View is only present in the Parent RecyclerView then childPos will be -1.
    abstract fun onResult(parentPos: Int, childPos: Int, courseUrl: String)
}

class RecyclerItemClickListener(context: Context?, private val mListener: OnItemClickListener?) :
    RecyclerView.OnItemTouchListener {

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    var mGestureDetector: GestureDetector
    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    init {
        mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }
}