package com.naumov.appmvp.core.nav

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.user.UserCardFragment
import com.naumov.appmvp.user.UserFragment

object UsersScreen {
    fun FragmentUser() = FragmentScreen{ UserFragment.getInstance()}
    fun FragmentUsercard(userID:GithubUserEntity) = FragmentScreen(){UserCardFragment.newInstance(userID)}
}