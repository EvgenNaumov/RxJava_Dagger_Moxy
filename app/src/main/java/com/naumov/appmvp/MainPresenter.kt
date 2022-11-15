package com.naumov.appmvp

class MainPresenter(val view:MainView) {

    val model = CountersModel()

    fun counterClickButton1(){
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun counterClickButton2(){
        val nextValue = model.next(1)
        view.setButton2text(nextValue.toString())
    }

    fun counterClickButton3(){
        val nextValue = model.next(2)
        view.setButton3text(nextValue.toString())
    }
}

