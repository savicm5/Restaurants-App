package com.example.restaurants.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurants.repository.ChosenRepository
import com.example.restaurants.repository.RestRepository

class ChosenViewModelFactory(private val restRepository : ChosenRepository,private val resType:String): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChosenViewModel(restRepository,resType) as T
    }
}