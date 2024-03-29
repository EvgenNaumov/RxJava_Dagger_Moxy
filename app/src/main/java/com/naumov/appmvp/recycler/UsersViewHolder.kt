package com.naumov.appmvp.recycler

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.R
import com.naumov.appmvp.core.nav.UsersScreen
import com.naumov.appmvp.databinding.ItemUserBinding
import com.naumov.appmvp.model.GithubUserEntity

class UsersViewHolder(private val binding: ItemUserBinding) :RecyclerView.ViewHolder(binding.root) {

    private val router:Router = App.instance.router

    fun bind(item:GithubUserEntity) {

        itemView.setOnClickListener {
            router.navigateTo(UsersScreen.screenUserCard(item.login, item.id))
        }

        with(binding) {
            loginUserTextView.text = item.login
            descriptionTextView.text = item.description
            fotoImage.load(item.avatarUrl)

        }
    }
}