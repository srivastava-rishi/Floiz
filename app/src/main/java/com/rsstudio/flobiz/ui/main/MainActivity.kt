package com.rsstudio.flobiz.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var logTag = "@MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}