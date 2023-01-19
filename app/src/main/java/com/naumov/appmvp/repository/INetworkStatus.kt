package com.naumov.appmvp.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun status():Observable<Boolean>
    fun statusSingle():Single<Boolean>
}