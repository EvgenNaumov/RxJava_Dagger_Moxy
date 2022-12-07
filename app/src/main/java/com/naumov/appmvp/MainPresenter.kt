package com.naumov.appmvp

import moxy.InjectViewState
import moxy.MvpPresenter

class MainPresenter(private val countersModel: CountersModel):MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun counterClickButtonOne(){
        val nextValue = countersModel.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }

    fun counterClickButtonTwo(){
        val nextValue = countersModel.next(1)
        viewState.setButtonTwoText(nextValue.toString())
    }

    fun counterClickButtonThree(){
        val nextValue = countersModel.next(2)
        viewState.setButtonThreeText(nextValue.toString())
    }
}

