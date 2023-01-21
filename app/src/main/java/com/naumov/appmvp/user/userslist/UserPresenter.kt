package com.naumov.appmvp.user.userslist

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.TAG
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubUserInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(
) : MvpPresenter<UserView>() {

    @Inject lateinit var repository: GithubUserInterface
    @Inject lateinit var router: Router

    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading()
        val disp = repository.getGithubUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                updateView(it)
            },
             {
                Log.d(TAG, "onFirstViewAttach: ${it.message}")
                viewState.errorView(it)
            })

        bag.addAll(disp)
    }

    private fun updateView(listUsers:List<GithubUserEntity>) {
        viewState.initView(listUsers)
        viewState.hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
    fun onBackPress(): Boolean {
        router.exit()
        return true
    }
}