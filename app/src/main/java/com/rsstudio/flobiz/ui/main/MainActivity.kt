package com.rsstudio.flobiz.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.ui.base.BaseActivity
import com.rsstudio.flobiz.ui.main.adapter.MainAdapter
import com.rsstudio.flobiz.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    var logTag = "@MainActivity"

    private lateinit var mainAdapter: MainAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //
        initRecyclerView()
        initObservers()
    }


    private fun initObservers() {

        viewModel.questionData.observe(this) {

            if (it != null) {
                Log.d(logTag, "initObservers: " + it.items[0].creation_date)
                mainAdapter.submitList(it.items,0)
                binding.iLoader.visibility = View.GONE
                binding.cvQuestionCard.visibility = View.VISIBLE
            }
        }

    }

    private fun initRecyclerView() {
        val llm = LinearLayoutManager(this)
        binding.rvForAskedQuestion.setHasFixedSize(true)
        binding.rvForAskedQuestion.layoutManager = llm
        mainAdapter = MainAdapter(this)
        binding.rvForAskedQuestion.adapter = mainAdapter
    }



}