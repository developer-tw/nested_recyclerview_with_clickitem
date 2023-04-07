package com.sqtek.nestedrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sqtek.nestedrecyclerview.databinding.CollectionItemBinding
import com.sqtek.nestedrecyclerview.model.MainModel
import com.sqtek.nestedrecyclerview.model.RvOnClickListener

class MainAdapter(private val collections: List<MainModel>, val clickReporter: RvOnClickListener):
        RecyclerView.Adapter<MainAdapter.CollectionViewHolder>() {

    inner class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
            Log.d("ETHAN=>","in Parent, CollectionViewHolder()")
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            Log.d("ETHAN=>","in Parent, onClick()")
            if (position != RecyclerView.NO_POSITION) {
                Log.d("ETHAN=>","in Parent, pos=${position}")
                clickReporter.onNestedRvClick("Parent", position, "null")
            }
        }
        val binding = CollectionItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.collection_item, parent, false)
        return CollectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.binding.apply {
            val collection= collections[position]
            textViewCollectionTitle.text =collection.title
            val courseAdapter = CourseAdapter(collection.courseModels, clickReporter)
            recyclerViewBooks.adapter= courseAdapter
        }
    }

    override fun getItemCount(): Int {
        return collections.size
    }
}