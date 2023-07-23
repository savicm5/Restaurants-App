package com.example.restaurants

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.api.ApiInterface
import com.example.restaurants.api.ApiUtiities
import com.example.restaurants.databinding.ActivityMainBinding
import com.example.restaurants.model.Result
import com.example.restaurants.repository.RestRepository
import com.example.restaurants.viewmodel.RestViewModel
import com.example.restaurants.viewmodel.RestViewModelFactory
import kotlin.math.roundToInt


class HomePageFragment : Fragment() {

    //dodao za nav pokusaj
    private lateinit var binding : ActivityMainBinding


    private lateinit var adapter: RestRecycleView
    private lateinit var recycleView: RecyclerView
    private lateinit var restList: ArrayList<com.example.restaurants.model.Result>


    //za retrofit
    private lateinit var restViewModel: RestViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     // return inflater.inflate(R.layout.fragment_home_page, container, false)

        val view = inflater.inflate(R.layout.fragment_home_page, container, false)

        restList = arrayListOf<com.example.restaurants.model.Result>()

        //za retrofit


        val layoutManager = LinearLayoutManager(context)
        recycleView = view.findViewById(R.id.recycler)
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        adapter = RestRecycleView(restList)
        recycleView.adapter = adapter



        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiInterface = ApiUtiities.getInstance().create(ApiInterface::class.java)

        val restRepository = RestRepository(apiInterface)
//nisam sig da l sam dobro video???
        restViewModel = ViewModelProvider(this, RestViewModelFactory(restRepository)).get(RestViewModel::class.java)



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


        adapter.setOnItemClickListener(object : RestRecycleView.onItemClickListener{
            override fun onItemClick(restaurantView: com.example.restaurants.model.Result) {
                println("res: ${restaurantView.name}")
                Log.d("SHUH",restaurantView.name)
                //Log.d("SHUH",arguments("name"))

                findNavController().navigate(R.id.action_homePageFragment_to_restaurantView,Bundle().apply {
                    putString("restName",restaurantView.name)
                    putString("restDescription",restaurantView.snippet)
                    putString("restAdress",restaurantView.location_id)
                    putString("restImage",restaurantView.images.first().source_url)
                    putString("restScore",((restaurantView.score*100).roundToInt()/100.0).toString())

                })
            }
        })

    }


}

