package com.naumov.appmvp.user

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.TAG
import com.naumov.appmvp.disposeBy
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.CountersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubInterface,
    private val router: Router
) : MvpPresenter<UserView>() {
    private val bag = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading()
        repository.getGithubUsers()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                viewState.initView(it)
                viewState.hideLoading()
            },
            {
                Log.d(TAG, "onFirstViewAttach: ${it.message}")
                viewState.errorView(it)
            }
        ).disposeBy(bag)

    }

    private fun create(list:List<GithubUserEntity>) = Observable.create<List<GithubUserEntity>> { emitter->
        try {
            if (list.isEmpty()){
                emitter.onError(RuntimeException("error loading users list"))
                return@create
            } else{
                emitter.onNext(list)
            }
            emitter.onComplete()
        }catch (t:Throwable){
            emitter.onError(RuntimeException("error loading users list"))
        }
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