package com.example.restaurants.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restaurants.api.ApiInterface
import com.example.restaurants.api.ChosenInterface
import com.example.restaurants.model.Data
import android.content.Context

class ChosenRepository(private val apiInterface: ChosenInterface) {
    private val restLiveData = MutableLiveData<Data>()

    val rest : LiveData<Data>

    get() = restLiveData

    suspend fun getRests(typeOfRes: String){

        val result = apiInterface.getData2("cuisine-${typeOfRes}")
        if(result.body() != null){
            restLiveData.postValue(result.body())
        }
    }
}