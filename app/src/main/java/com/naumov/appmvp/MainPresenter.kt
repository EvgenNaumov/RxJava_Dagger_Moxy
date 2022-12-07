package com.naumov.appmvp

import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.CountersRepository
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import moxy.MvpPresenter

class MainPresenter(private val repository: GithubInterface):MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initList(repository.getGithubUsers())
    }


}

