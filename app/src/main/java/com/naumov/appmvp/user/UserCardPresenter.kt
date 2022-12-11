package com.naumov.appmvp.user

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.model.GithubUserEntity
import moxy.MvpPresenter

class UserCardPresenter(private val router:Router):MvpPresenter<UserCardView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    fun onBackPress(): Boolean {
        router.exit()
        return true
    }

}