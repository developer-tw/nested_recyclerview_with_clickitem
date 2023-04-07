package com.sqtek.nestedrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sqtek.nestedrecyclerview.databinding.CourseItemBinding
import com.sqtek.nestedrecyclerview.model.CourseModel
import com.sqtek.nestedrecyclerview.model.RvOnClickListener

class CourseAdapter(private val courseModels: List<CourseModel>, val clickReporter: RvOnClickListener):
        RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                Log.d("ETHAN=>","in Child, pos=$adapterPosition, url=${courseModels[position].courseUrl}")
                clickReporter.onNestedRvClick("Child", position, courseModels[position].courseUrl)
            }
        }
        val binding = CourseItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.binding.apply {
            imageViewBook.setImageResource(courseModels[position].imageId)
        }
    }

    override fun getItemCount(): Int {
        return courseModels.size
    }
}