package com.example.restaurants.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.model.Data
import com.example.restaurants.repository.ChosenRepository
import com.example.restaurants.repository.RestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChosenViewModel(private val restRepository: ChosenRepository,private val restType : String): ViewModel() {



    init {
        viewModelScope.launch(Dispatchers.IO) {
            restRepository.getRests(restType)
        }

    }

    val rests : LiveData<Data>

        get() = restRepository.rest
}