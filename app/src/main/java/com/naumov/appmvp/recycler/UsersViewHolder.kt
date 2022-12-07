package com.naumov.appmvp.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naumov.appmvp.R
import com.naumov.appmvp.model.GithubUserEntity
import kotlin.math.log

class UsersViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    private val loginUser by lazy { itemView.findViewById<TextView>(R.id.login_user_text_view) }

    fun bind(item:GithubUserEntity) = with(item){
        loginUser.text = login

    }

}