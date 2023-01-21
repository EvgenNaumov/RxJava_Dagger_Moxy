package com.naumov.appmvp.user.userdetails

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.TAG
import com.naumov.appmvp.repository.GithubRepoInterface
import com.naumov.appmvp.repository.GithubUserInterface
import com.naumov.appmvp.subscribeSingeByDef
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserCardPresenter() : MvpPresenter<UserCardView>() {

    @Inject lateinit var repoUser: GithubUserInterface
    @Inject lateinit var repoRepos:GithubRepoInterface
    @Inject lateinit var router: Router

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
            .subscribeSingeByDef()
            .subscribe(
                {
                    viewState.updateList(it)
                    viewState.hideLoading()

                },
                {
                    Log.d(TAG, "onFirstViewAttach: ${it.message}")
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