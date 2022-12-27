package com.naumov.appmvp.user.userdetails

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.disposeBy
import com.naumov.appmvp.subscribeSingeByDef
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserCardPresenter(private val router: Router) : MvpPresenter<UserCardView>() {
    private val repo = App.instance.repo
    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        repo.getUserByid(login)
            .subscribeSingeByDef()
            .subscribe(
                {
                    viewState.initView()
                },
                {

                }
            )

    }

    fun loadRepoUser(login: String){
        viewState.showLoading()

        repo.getUserRepoById(login)
            .delay(3000, TimeUnit.MILLISECONDS)
            .subscribeSingeByDef()
            .subscribe(
                {
                    viewState.updateList(it)
                    viewState.hideLoading()

                },
                {

                }
            )
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