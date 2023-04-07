package com.sqtek.nestedrecyclerview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sqtek.nestedrecyclerview.databinding.ActivityMainBinding
import com.sqtek.nestedrecyclerview.model.RecyclerItemClickListener
import com.sqtek.nestedrecyclerview.model.RvOnClickListener
import com.sqtek.nestedrecyclerview.model.SampleData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var clickReporter: RvOnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickReporter = object: RvOnClickListener() {
            override fun onResult(parentPos: Int, childPos: Int, courseUrl: String) {
                Log.d("ETHAN=>","onResult(): $parentPos, Child Position: $childPos")
                sendIntent(courseUrl)
            }
        }
        // Set Touch Listeners for each Section in Parent RecyclerView:
        binding.recyclerViewCollections.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(v: View?, position: Int) {
                        Log.i("ETHAN=>","In parent pos=$position")
                        clickReporter.onNestedRvItemClick(position)
                    }
                }
            )
        )

        binding.apply {
            recyclerViewCollections.adapter = MainAdapter(SampleData.collections, clickReporter)
        }
    }

    fun sendIntent(courseUrl: String) {
        val intent = Intent(this,DetailViewActivity::class.java)
        intent.putExtra("course_url", courseUrl)
        intent.apply {
            startActivity(this)
        }
    }
}