package com.naumov.appmvp.main

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.core.nav.UsersScreen
import moxy.MvpPresenter

class MainPresenter(private val router: Router):MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreen.screenUsersList())
    }

    fun onBackPressed() {
        router.exit()
    }


}

