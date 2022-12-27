package com.naumov.appmvp.user.userdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.core.nav.UsersScreen
import com.naumov.appmvp.databinding.ItemRepoBinding
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity

class UserDetailAdapter() : RecyclerView.Adapter<UserDetailAdapter.RepoViewHolder>() {

    var listRepo: List<UserRepoEntity> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context))
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = listRepo.size

    fun getItem(position:Int):UserRepoEntity = listRepo[position]

    inner class RepoViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

        private val router: Router = App.instance.router

        fun bind(item: UserRepoEntity) {

            itemView.setOnClickListener {
                router.navigateTo(UsersScreen.screenRepoFoks(item.forksUrl))
            }

            with(binding){
                descriptionRepo.text = item.forksUrl
                nameRepo.text = item.name
            }
        }
    }


}