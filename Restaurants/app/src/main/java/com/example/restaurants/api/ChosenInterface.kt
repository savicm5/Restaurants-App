package com.example.restaurants.api

import com.example.restaurants.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChosenInterface {

    @GET("api/20220411/poi.json?account=E9CHL2VB&token=81vio7opy5th0q8z5b3ch7whyophexp0&location_id=Paris")
    suspend fun getData2(
        @Query("tag_labels") type : String
    ): Response<Data>

}