package com.example.prctican2adrianacua.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.model.Usuario

//class UsersAdapter(var items: List<Usuario>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        var textViewUsername = view.findViewById<TextView>(R.id.textViewUsernameItem)
//
//        fun onBind(user : Usuario) {
//            textViewUsername.text = user.username
//        }
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_ingredients, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
//        holder.onBind(items[position])
//    }
//
//    override fun getItemCount(): Int = items.size
//}
