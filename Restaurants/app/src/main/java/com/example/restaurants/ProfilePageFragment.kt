package com.example.restaurants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurants.databinding.FragmentProfilePageBinding


class ProfilePageFragment : Fragment() {

    private var _binding : FragmentProfilePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_page, container, false)

        _binding = FragmentProfilePageBinding.inflate(inflater,container,false)

        val logOutBtn : Button = view.findViewById(R.id.button3)

        logOutBtn.setOnClickListener {
            val intent = Intent(this@ProfilePageFragment.requireContext(), LoginActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener{
            findNavController().navigate(R.id.action_profilePageFragment_to_loginActivity)
        }


        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        val valueName = sharedPreferences.getString("name","NO VALUE")

        val valueLastName = sharedPreferences.getString("lastName","NO VALUE")

        val valueEmail = sharedPreferences.getString("email","NO VALUE")

        val valuePass = sharedPreferences.getString("password","NO VALUE")

        val valueTypeOfRes = sharedPreferences.getString("typeOfRes","NO VALUE")


        binding.editTextTextPersonName6.setText(valueName)
        binding.editTextTextPersonName7.setText(valueLastName)
        binding.editTextTextPersonName8.setText(valueEmail)
        binding.editTextTextPersonName9.setText(valueTypeOfRes)

        val editNameBtn = binding.checkBox
        val editLastNameBtn = binding.checkBox2
        val editTypeOfRes = binding.checkBox3

        editNameBtn.setOnCheckedChangeListener{_, isChecked->

            if (isChecked){

                val valueName = sharedPreferences.getString("name","NO VALUE")
                binding.editTextTextPersonName6.setText(valueName)

            }
            else{

                val name = binding.editTextTextPersonName6.text.toString().trim()
                writeNameToSharedPref(name)

            }

        }

        editLastNameBtn.setOnCheckedChangeListener{_, isChecked->

            if (isChecked){

                val valueLastName = sharedPreferences.getString("lastName","NO VALUE")
                binding.editTextTextPersonName7.setText(valueLastName)

            }
            else{

                val lastName = binding.editTextTextPersonName7.text.toString().trim()
                writeLastNameToSharedPref(lastName)

            }

        }

        editTypeOfRes.setOnCheckedChangeListener{_, isChecked->

            if (isChecked){

                val valueTypeOfRes = sharedPreferences.getString("typeOfRes","NO VALUE")
                binding.editTextTextPersonName9.setText(valueTypeOfRes)

            }
            else{

                val typeOfRes = binding.editTextTextPersonName9.text.toString().trim()
                writeTypeOfResToSharedPref(typeOfRes)

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun writeNameToSharedPref(name: String){
        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        with(sharedPreferences.edit()){
            putString("name",name)
            apply()
        }

    }

    private fun writeLastNameToSharedPref(lastName: String){
        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        with(sharedPreferences.edit()){
            putString("lastName",lastName)
            apply()
        }

    }

    private fun writeTypeOfResToSharedPref(typeOfRes: String){
        val sharedPreferences = context?.getSharedPreferences("prefsFile", Context.MODE_PRIVATE)?: return

        with(sharedPreferences.edit()){
            putString("typeOfRes",typeOfRes)
            apply()
        }

    }




}