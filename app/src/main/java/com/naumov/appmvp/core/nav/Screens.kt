package com.naumov.appmvp.core.nav

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.user.repodetails.RepoDetailsFragment
import com.naumov.appmvp.user.userdetails.UserCardFragment
import com.naumov.appmvp.user.userslist.UserFragment

object UsersScreen {
    fun screenUsersList() = FragmentScreen{ UserFragment.getInstance()}
    fun screenUserCard(userID:String) = FragmentScreen(){ UserCardFragment.newInstance(userID)}
    fun screenRepoFoks(forkUrl:String) = FragmentScreen(){ RepoDetailsFragment.newInstance(forkUrl)}
}