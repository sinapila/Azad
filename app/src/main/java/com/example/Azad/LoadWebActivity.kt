package com.example.Azad

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoadWebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_web)
        val intent = intent
        val url = intent.getStringExtra("url")

        val myWebView: WebView = findViewById(R.id.webview)
        val webSettings = myWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(false)
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        if (url != null) {
            myWebView.loadUrl(url)
        }else{
            Toast.makeText(this, "خطای ناشناخته", Toast.LENGTH_SHORT).show()
        }

    }
}