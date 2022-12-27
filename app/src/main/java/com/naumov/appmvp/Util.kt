package com.naumov.appmvp

import android.opengl.Visibility
import android.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

const val TAG = "TAG_LOG"
const val KEY_POS_LIST = "rey_pos_list"

 fun View.showView(){
    this.visibility = View.VISIBLE
}

 fun View.hideView(){
    this.visibility = View.GONE
}

 fun <T> Single<T>.subscribeSingeByDef():Single<T>{
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

 fun Disposable.disposeBy(bag:CompositeDisposable){
    bag.add(this)
}