package com.cemreonur.kotlinroomyoutube.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cemreonur.kotlinroomyoutube.R
import com.cemreonur.kotlinroomyoutube.adapter.RecyclerViewAdapter
import com.cemreonur.kotlinroomyoutube.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Init RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { userList ->
            recyclerViewAdapter.setData(userList)
            if (userList.size == 0) {
                Toast.makeText(context, "Liste Boş", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

}