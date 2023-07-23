package com.example.restaurants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.model.Data
import com.example.restaurants.repository.RestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestViewModel(private val restRepository: RestRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            restRepository.getRests()

        }

    }

    val rests : LiveData<Data>

    get() = restRepository.rest



}