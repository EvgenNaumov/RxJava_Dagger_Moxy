package com.naumov.appmvp

import com.naumov.appmvp.model.GithubUserEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun initList(list: List<GithubUserEntity>)
    fun updateList(list: List<GithubUserEntity>)
}