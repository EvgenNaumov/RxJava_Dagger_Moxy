package com.naumov.appmvp.user

import com.naumov.appmvp.model.GithubUserEntity
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserCardView:MvpView {
    fun initView()
    fun updateView()
}
