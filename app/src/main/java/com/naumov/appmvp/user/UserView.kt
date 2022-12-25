package com.naumov.appmvp.user

import com.naumov.appmvp.model.GithubUserEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun initView(usersList: List<GithubUserEntity>)
    fun updateView(usersList: List<GithubUserEntity>)
    fun errorView(error:Throwable)
    fun showLoading()
    fun hideLoading()
}
