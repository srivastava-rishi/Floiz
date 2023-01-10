package com.rsstudio.flobiz.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.ui.base.BaseActivity
import com.rsstudio.flobiz.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var logTag = "@MainActivity"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initObservers()
    }


    private fun initObservers() {

        viewModel.questionData.observe(this) {

            if (it != null) {
                Log.d(logTag, "initObservers: " + "line no 32" + it.items)
            }
        }

    }

}