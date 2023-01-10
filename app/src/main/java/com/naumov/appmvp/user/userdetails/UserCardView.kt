package com.naumov.appmvp.user.userdetails

import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserCardView:MvpView {
    fun initView(login:String)
    fun updateList(repoList: List<UserRepoEntity>)
    fun updateView()
    fun showLoading()
    fun hideLoading()
}
