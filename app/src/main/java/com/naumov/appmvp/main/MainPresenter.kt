package com.naumov.appmvp.main

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.core.nav.UsersScreen
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter():MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: UsersScreen

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.screenUsersList())
    }

    fun onBackPressed() {
        router.exit()
    }


}

