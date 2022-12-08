package com.naumov.appmvp.user

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.CountersRepository
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubInterface,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView(repository.getGithubUsers())
    }

    fun onBackPress(): Boolean {
        router.exit()
        return true
    }
}