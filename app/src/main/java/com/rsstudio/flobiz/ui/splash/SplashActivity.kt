package com.rsstudio.flobiz.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.databinding.ActivitySplashBinding
import com.rsstudio.flobiz.ui.base.BaseActivity
import com.rsstudio.flobiz.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    var logTag = "@SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        //
        gotoMainActivity()

    }

    private fun gotoMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 500
        )
    }
}