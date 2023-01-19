package com.naumov.appmvp.di

import com.naumov.appmvp.main.MainActivity
import com.naumov.appmvp.main.MainPresenter
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import com.naumov.appmvp.user.userdetails.UserCardPresenter
import com.naumov.appmvp.user.userslist.UserPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModul::class,
        CiceroneModule::class,
        RepoModule::class,
        ApiModule::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(userPresenter: UserPresenter)

}