package com.example.restaurants

import com.example.restaurants.model.Image
import java.net.Inet4Address

//ako imam vise elemenata moram sve da ih navedem kao argumente
//promena
data class ReastaurantsList(var titleImage: Int,var heading: String,var address: String,var description:String,var image: Image)
