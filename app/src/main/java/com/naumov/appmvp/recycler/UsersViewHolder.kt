package com.naumov.appmvp.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.R
import com.naumov.appmvp.core.nav.UsersScreen
import com.naumov.appmvp.model.GithubUserEntity

class UsersViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    private val loginUser by lazy { itemView.findViewById<TextView>(R.id.login_user_text_view) }
    private val descriptionUser by lazy { itemView.findViewById<TextView>(R.id.description_text_view)}
    private val router:Router = App.instance.router

    fun bind(item:GithubUserEntity) {

        itemView.setOnClickListener {
            router.navigateTo(UsersScreen.screenUserCard(item))
        }

        with(item) {
            loginUser.text = login
            descriptionUser.text = description
        }
    }
}