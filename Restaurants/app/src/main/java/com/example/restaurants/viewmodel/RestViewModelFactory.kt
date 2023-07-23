package com.example.restaurants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurants.repository.RestRepository

class RestViewModelFactory(private val restRepository : RestRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RestViewModel(restRepository) as T
    }


}