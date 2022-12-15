package com.naumov.appmvp.user

import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.CountersRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserPresenter(
    private val repository: GithubInterface,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val observerGithub = object : Observer<List<GithubUserEntity>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<GithubUserEntity>) {
                viewState.initView(t)
            }

            override fun onError(e: Throwable) {
                viewState.errorView(e)
            }

            override fun onComplete() {

             }
        }


//        Observable.fromCallable { return@fromCallable repository.getGithubUsers() }
//            .subscribe { run { viewState.initView(it) } }
//        viewState.initView(repository.getGithubUsers())

        create(repository.getGithubUsers()).subscribe(observerGithub)
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


    fun onBackPress(): Boolean {
        router.exit()
        return true
    }
}