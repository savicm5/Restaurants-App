package com.example.restaurants

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.api.ApiUtiities
import com.example.restaurants.api.ChosenInterface
import com.example.restaurants.model.Result
import com.example.restaurants.repository.ChosenRepository
import com.example.restaurants.viewmodel.ChosenViewModel
import com.example.restaurants.viewmodel.ChosenViewModelFactory
import kotlin.math.roundToInt


private lateinit var restViewModel: ChosenViewModel


class FavoritesPageFragment : Fragment() {

    private lateinit var adapter: RestRecycleView
    private lateinit var recycleView: RecyclerView
    private lateinit var restList: ArrayList<com.example.restaurants.model.Result>


    private var valueTypeOfRes : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return
        valueTypeOfRes = sharedPreferences.getString("typeOfRes","Pizza")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites_page, container, false)

        restList = arrayListOf<com.example.restaurants.model.Result>()

        //za retrofit

        val apiInterface = ApiUtiities.getInstance().create(ChosenInterface::class.java)

        val restRepository = ChosenRepository(apiInterface)
//nisam sig da l sam dobro video???
        restViewModel = ViewModelProvider(this, ChosenViewModelFactory(restRepository,valueTypeOfRes.toString())).get(
            ChosenViewModel::class.java)



        restViewModel.rests.observe(viewLifecycleOwner) {

            for (restaurant in it.results) {
                restList.add(
                    Result(
                        restaurant.coordinates,
                        restaurant.images,
                        restaurant.location_id,
                        restaurant.name,
                        restaurant.price_tier,
                        restaurant.score,
                        restaurant.snippet,
                    )
                )
            }
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recycleView = view.findViewById(R.id.recycler_chosen)
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        adapter = RestRecycleView(restList)
        recycleView.adapter = adapter

        adapter.setOnItemClickListener(object : RestRecycleView.onItemClickListener{
            override fun onItemClick(restaurantView: Result) {
                println("res: ${restaurantView.name}")
                findNavController().navigate(R.id.action_favoritesPageFragment_to_restaurantView,Bundle().apply {
                    putString("restName",restaurantView.name)
                    putString("restDescription",restaurantView.snippet)
                    putString("restAdress",restaurantView.location_id)
                    if(restaurantView.images.isEmpty()){
                        putString("restImage", "https://i.insider.com/596e3d09dde1891cc024879d?width=1000&format=jpeg&auto=webp")
                    }
                    else {
                        putString("restImage", restaurantView.images.first().source_url)
                    }
                    putString("restScore",((restaurantView.score*100).roundToInt()/100.0).toString())
                })

            }
        })
    }

}