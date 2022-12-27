package com.naumov.appmvp.user.repodetails

import android.util.Log
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.App
import com.naumov.appmvp.TAG
import com.naumov.appmvp.subscribeSingeByDef
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

class RepoDetailsPresenter(private val router:Router):MvpPresenter<RepoDetailsView>() {

    private val repo = App.instance.repo
    private val bag = CompositeDisposable()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    override fun detachView(view: RepoDetailsView?) {
        super.detachView(view)
    }

    fun loadDetailRepo(forkUrl:String){

        repo.getForksByUrl(forkUrl)
            .subscribeSingeByDef()
            .subscribe(
                {
                    Log.d(TAG, "loadDetailRepo: ${it.size}")
                    viewState.updateQuantityFork(it.size)

                },
                {
                    Log.d(TAG, "loadDetailRepo: ${it.message}")
                }
            )

    }

    fun onBackPress(): Boolean {
        router.exit()
        return true
    }


}