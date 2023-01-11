package com.rsstudio.flobiz.ui.webView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.databinding.ActivityWebViewBinding
import com.rsstudio.flobiz.ui.base.BaseActivity

class WebViewActivity : BaseActivity(),  View.OnClickListener {


    lateinit var binding: ActivityWebViewBinding

    var openLink = ""

    var logTag = "@WebViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        //
        initAction()
        loadData()
        webViewSetUp()
    }

    private fun initAction() {
        binding.ivBack.setOnClickListener(this)
    }


    private fun loadData(){
        openLink = intent.getStringExtra("link").toString()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetUp(){
        binding.webView.webViewClient =  WebViewClient()
        binding.webView.apply {
            loadUrl(openLink!!)
            settings.javaScriptEnabled = true
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            finish()
        }
    }


    inner class WebViewClient : android.webkit.WebViewClient() {

        // Load the URL
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.iLoader.visibility = View.GONE
            binding.webView.visibility = View.VISIBLE
            binding.iAppBar.visibility = View.VISIBLE
        }
    }

    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.ivBack -> {
                finish()
            }
        }
    }
}