package com.naumov.appmvp.core.nav

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.naumov.appmvp.user.UserFragment

object UsersScreen {
    fun FragmentUser() = FragmentScreen{ UserFragment.getInstance()}
}