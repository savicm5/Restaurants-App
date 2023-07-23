package com.example.restaurants

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurants.databinding.FragmentLoginBinding



class LogInFragment : Fragment() {

/*
companion object {
const val PREFS_NAME = "prefsName"
const val KEY = "key"
}
*/

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        _binding = FragmentLoginBinding.inflate(inflater,container,false)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textView2.setOnClickListener{
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
//
        binding.button.setOnClickListener{


            val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return@setOnClickListener


            val valueEmail = sharedPreferences.getString("email","NO VALUE")
            val valuePass = sharedPreferences.getString("password","NO VALUE")

            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val pass = binding.editTextTextPassword.text.toString().trim()

            if (email == valueEmail && pass == valuePass){
                findNavController().navigate(R.id.action_logInFragment_to_mainActivity)

            }
            else{
                Toast.makeText(context,"Wrong email or password", Toast.LENGTH_LONG).show()

            }


        }
//
        binding.textView.setOnClickListener{
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}