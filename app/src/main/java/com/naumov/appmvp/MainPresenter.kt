package com.naumov.appmvp

class MainPresenter(val view:MainView) {

    val model = CountersModel()

    fun counterClickButton1(){
        val nextValue = model.next(0)
        view.setButtonOneText(nextValue.toString())
    }

    fun counterClickButton2(){
        val nextValue = model.next(1)
        view.setButtonTwoText(nextValue.toString())
    }

    fun counterClickButton3(){
        val nextValue = model.next(2)
        view.setButtonThreeText(nextValue.toString())
    }
}

