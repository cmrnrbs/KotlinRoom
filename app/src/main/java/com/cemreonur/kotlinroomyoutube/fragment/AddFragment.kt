package com.cemreonur.kotlinroomyoutube.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cemreonur.kotlinroomyoutube.R
import com.cemreonur.kotlinroomyoutube.data.User
import com.cemreonur.kotlinroomyoutube.data.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class AddFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editName = view.findViewById<TextInputEditText>(R.id.editName)
        val editAge = view.findViewById<TextInputEditText>(R.id.editAge)
        val insertButton = view.findViewById<Button>(R.id.insertButton)

        insertButton.setOnClickListener {
            val user = User(0, editName.text.toString(), editAge.text.toString().toInt())
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Kullanıcı Eklendi!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }


}