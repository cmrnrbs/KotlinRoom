package com.cemreonur.kotlinroomyoutube.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cemreonur.kotlinroomyoutube.R
import com.cemreonur.kotlinroomyoutube.data.User
import com.cemreonur.kotlinroomyoutube.data.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class UpdateFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val editName = view.findViewById<TextInputEditText>(R.id.editName)
        val editAge = view.findViewById<TextInputEditText>(R.id.editAge)

        editName.setText(args.currentUser.name)
        editAge.setText(args.currentUser.age.toString())


        val updateButton = view.findViewById<Button>(R.id.updateButton)

        updateButton.setOnClickListener {
            val updateUser =
                User(args.currentUser.id, editName.text.toString(), editAge.text.toString().toInt())
            userViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Kullanıcı Güncellendi!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("${args.currentUser.name} mi Silinecek ?")
            builder.setMessage("${args.currentUser.name} kullanıcısını silmek istediğinize emin misiniz ?")
            builder.setPositiveButton("Evet") { _, _ ->
                userViewModel.deleteUser(args.currentUser)
                Toast.makeText(
                    requireContext(),
                    "${args.currentUser.name} isimli Kullanıcı Silindi!",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("Hayır") { _, _ -> }
            builder.create().show()
        }

        return super.onOptionsItemSelected(item)
    }
}