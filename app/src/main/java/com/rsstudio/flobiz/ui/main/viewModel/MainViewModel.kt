package com.rsstudio.flobiz.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsstudio.flobiz.data.network.model.Question
import com.rsstudio.flobiz.data.network.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository
) : ViewModel() {

    var logTag = "@MainViewModel"

    private val _questionData: MutableLiveData<Question> = MutableLiveData()
    val questionData: LiveData<Question> get() = _questionData

    init {
        getQuestionData()
    }

    private fun getQuestionData() {

        viewModelScope.launch {

            val result = repository.getQuestionData()

            Log.d(logTag, "getAthleteData: $result")

            _questionData.value = result

        }
    }


}