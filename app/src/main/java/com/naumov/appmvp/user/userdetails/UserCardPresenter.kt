package com.naumov.appmvp.user.userdetails

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.subscribeSingeByDef
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class UserCardPresenter(private val router: Router) : MvpPresenter<UserCardView>() {

    private val repoUser = App.instance.repoUser
    private val repoRepos = App.instance.repoRepos
    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun loadUser(login: String) {
        val dispUser = repoUser.getUserByid(login)
            .subscribeSingeByDef()
            .subscribe(
                {
                    viewState.initView(it.login)
                },
                {

                }
            )

        bag.addAll(dispUser)
    }

    fun loadRepoUser(login: String, userID:Long){
        viewState.showLoading()

        val dispRepo = repoRepos.getUserRepoById(login,userID)
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
        bag.addAll(dispRepo)
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