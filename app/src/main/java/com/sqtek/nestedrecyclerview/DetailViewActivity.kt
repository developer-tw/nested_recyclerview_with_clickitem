package com.sqtek.nestedrecyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.sqtek.nestedrecyclerview.databinding.ActivityDetailViewBinding

class DetailViewActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityDetailViewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("course_url")
        dataBinding =
            DataBindingUtil.setContentView<ActivityDetailViewBinding>(this, R.layout.activity_detail_view)
        val webView = dataBinding.webView
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url.toString())
        webView.settings.setSupportZoom(true)
    }
}