package com.cemreonur.kotlinroomyoutube.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cemreonur.kotlinroomyoutube.R
import com.cemreonur.kotlinroomyoutube.data.User
import com.cemreonur.kotlinroomyoutube.fragment.ListFragmentDirections

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtName = view.findViewById<TextView>(R.id.txtName)
        val txtAge = view.findViewById<TextView>(R.id.txtAge)
        fun bind(user: User) {
            txtName.setText(user.name)
            txtAge.setText(user.age.toString())
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val currentUser = userList.get(position)
        holder.bind(currentUser)
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUser)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}