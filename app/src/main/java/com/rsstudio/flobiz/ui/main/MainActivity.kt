package com.rsstudio.flobiz.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsstudio.flobiz.R
import com.rsstudio.flobiz.data.network.model.Item
import com.rsstudio.flobiz.databinding.ActivityMainBinding
import com.rsstudio.flobiz.ui.base.BaseActivity
import com.rsstudio.flobiz.ui.main.adapter.MainAdapter
import com.rsstudio.flobiz.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity()  {

    lateinit var binding: ActivityMainBinding

    var logTag = "@MainActivity"

    private lateinit var mainAdapter: MainAdapter

    var temp = 0

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //
        getValue()
        initRecyclerView()
        initView()
        initObservers()

    }

    private fun getValue() {

      temp = pref.getRemoveAdsValue()

    }

    private fun initObservers() {

        viewModel.questionData.observe(this) {

            if (it != null) {
                Log.d(logTag, "initObservers: " + it.items[0].creation_date)
                var list: MutableList<Item> = mutableListOf()

                    var ad = Item(
                        content_license = "ADVERTISEMENT",
                        type = 1
                    )
                    list.addAll(it.items)
                    list.add(ad)
                mainAdapter.submitList(list,0)
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

    private fun initView() {

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(logTag, "onTextChanged: $s")
                mainAdapter.filter.filter(s)
            }
        })

    }



}