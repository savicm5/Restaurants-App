package com.example.restaurants

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurants.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        _binding = FragmentRegisterBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener{

            val name = binding.editTextTextPersonName.text.trim().toString()
            val lastName = binding.editTextTextPersonName2.text.trim().toString()
            val email = binding.editTextTextEmailAddress2.text.trim().toString()
            val pass = binding.editTextTextPassword2.text.trim().toString()
            val repeated_pass = binding.editTextTextPassword3.text.trim().toString()
            val typeOfrest = binding.editTypeOfRest.text.trim().toString()

            if(pass == repeated_pass) {
                writeToSharedPref(name, lastName, email, pass, typeOfrest)

                //printRead()

                findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
            }
            else{
                Toast.makeText(context,"error repeating password",Toast.LENGTH_LONG).show()
            }



        }
//
        binding.textView3.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun writeToSharedPref(name: String, lastName: String,email: String,pass: String,typeOfrest: String){
        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        with(sharedPreferences.edit()){
            putString("name",name)
            putString("lastName",lastName)
            putString("password",pass)
            putString("email",email)
            putString("typeOfRes",typeOfrest)

            apply()
        }

    }

    private fun printRead(){
        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        val valueName = sharedPreferences.getString("name","NO VALUE")
        println(valueName)

        val valueLastName = sharedPreferences.getString("lastName","NO VALUE")
        println(valueLastName)

        val valueEmail = sharedPreferences.getString("email","NO VALUE")
        println(valueEmail)

        val valuePass = sharedPreferences.getString("password","NO VALUE")
        println(valuePass)

        val valueTypeOfRes = sharedPreferences.getString("typeOfRes","NO VALUE")
        println(valueTypeOfRes)

    }



}