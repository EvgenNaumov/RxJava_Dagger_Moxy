package com.naumov.appmvp.user

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.disposeBy
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.subscribeSingeByDef
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UserCardPresenter(private val router: Router) : MvpPresenter<UserCardView>() {
    private val repo = App.instance.repo
    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()
    }

    fun loadUser(login: String) {
        viewState.showLoading()
        repo.getUserByid(login)
            .subscribeSingeByDef()
            .subscribe(
                {
                viewState.updateView()
                    viewState.hideLoading()
                },
                {

                }
            ).disposeBy(bag)

    }

    fun onBackPress(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

}