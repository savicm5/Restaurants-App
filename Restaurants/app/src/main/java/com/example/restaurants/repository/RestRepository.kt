package com.example.restaurants.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.restaurants.api.ApiInterface
import com.example.restaurants.model.Data

class RestRepository(private val apiInterface: ApiInterface) {

    private val restLiveData = MutableLiveData<Data>()

    val rest : LiveData<Data>

    get() = restLiveData

    suspend fun getRests(){
        val result = apiInterface.getData()
        if(result.body() != null){
            restLiveData.postValue(result.body())
        }
    }
}