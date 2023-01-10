package com.rsstudio.flobiz.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {


    companion object {
        private lateinit var binding: ActivityBaseBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)
    }
}