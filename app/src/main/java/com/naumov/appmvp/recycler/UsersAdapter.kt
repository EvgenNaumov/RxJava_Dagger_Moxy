package com.naumov.appmvp.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naumov.appmvp.R
import com.naumov.appmvp.model.GithubUserEntity


class UsersAdapter():RecyclerView.Adapter<UsersViewHolder>() {

    var users:List<GithubUserEntity> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return UsersViewHolder(view)
    }

    override fun getItemCount() = users.size


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position:Int):GithubUserEntity = users[position]

    private fun setItem(position: Int){

    }
}