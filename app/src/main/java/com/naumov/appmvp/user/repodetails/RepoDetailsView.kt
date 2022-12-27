package com.naumov.appmvp.user.repodetails

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoDetailsView:MvpView {
    fun initVeiw(login:String, name:String)
    fun updateQuantityFork(fork:Int)
}