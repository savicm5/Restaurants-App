package com.example.restaurants

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.restaurants.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navControler : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    //        ODAVDE NAV BAR

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        //novo
        navControler = Navigation.findNavController(this,R.id.nav_host_fragment_content_main)
        setupWithNavController(binding.bottomNavigationView,navControler)

    }


    /*
    NAV BAR
    private fun replaceFragment(fragment: Fragment){

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    fragmentTransaction.replace(R.id.frame_layout,fragment)
    fragmentTransaction.commit()

    }
    */


}