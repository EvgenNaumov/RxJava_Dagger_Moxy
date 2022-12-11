package com.naumov.appmvp.recycler

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.naumov.appmvp.App
import com.naumov.appmvp.R
import com.naumov.appmvp.core.nav.UsersScreen
import com.naumov.appmvp.main.MainView
import com.naumov.appmvp.model.GithubUserEntity

class UsersViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    private val loginUser by lazy { itemView.findViewById<TextView>(R.id.login_user_text_view) }
    private val descriptionUser by lazy { itemView.findViewById<TextView>(R.id.description_text_view)}
    private val router:Router = App.instance.router

    fun bind(item:GithubUserEntity) {

        itemView.setOnClickListener {
            Toast.makeText(itemView.context, "open card",Toast.LENGTH_SHORT).show()
            router.navigateTo(UsersScreen.FragmentUsercard())
        }

        with(item) {
            loginUser.text = login
            descriptionUser.text = description
        }
    }
}