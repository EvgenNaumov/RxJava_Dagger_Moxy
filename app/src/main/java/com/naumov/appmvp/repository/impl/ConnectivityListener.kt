package com.naumov.appmvp.repository.impl

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.naumov.appmvp.repository.INetworkStatus
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ConnectivityListener(context: Context): INetworkStatus {

    private val subject:BehaviorSubject<Boolean> = BehaviorSubject.create()
    init{
        subject.onNext(false)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.requestNetwork(request, object:ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                subject.onNext(true)
            }

            override fun onLost(network: Network) {
                subject.onNext(false)
            }

            override fun onUnavailable() {
               subject.onNext(false)
            }
        })
    }

    override fun status():Observable<Boolean> = subject
    override fun statusSingle():Single<Boolean> = subject.first(false)

}