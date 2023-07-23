package com.example.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.restaurants.databinding.FragmentRestaurantViewBinding
import kotlinx.android.synthetic.main.fragment_restaurant_view.*


class RestaurantView : Fragment() {

    private var _binding : FragmentRestaurantViewBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRestaurantViewBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtName : TextView = view.findViewById(R.id.restNameTxt)
        val restName = requireArguments().getString("restName").toString()
        txtName.text = restName
        val pom = txtName.text.toString()

        val btn : Button = view.findViewById(R.id.button4)
        btn.text = restName

        val restDesc = requireArguments().getString("restDescription").toString()
        val txtDesc : TextView = view.findViewById(R.id.textView8)
        txtDesc.text = restDesc

        val txtAdress : TextView = view.findViewById(R.id.textView7)
        val restAdress = requireArguments().getString("restAdress").toString()
        txtAdress.text = restAdress

        val url = requireArguments().getString("restImage").toString()
        Glide.with(this)
            .load(url)
            .into(this.imageView3)

        val txtScore : TextView = view.findViewById(R.id.scoreTxt)
        val restScore = requireArguments().getString("restScore").toString()
        txtScore.text = restScore


        binding.button4.setOnClickListener{
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}