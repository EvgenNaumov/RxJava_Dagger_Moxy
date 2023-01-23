package com.naumov.appmvp.di

import com.naumov.appmvp.main.MainActivity
import com.naumov.appmvp.user.repodetails.RepoDetailsFragment
import com.naumov.appmvp.user.repodetails.RepoDetailsPresenter
import com.naumov.appmvp.user.userdetails.UserCardFragment
import com.naumov.appmvp.user.userdetails.UserCardPresenter
import com.naumov.appmvp.user.userslist.UserFragment
import com.naumov.appmvp.user.userslist.UserPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModul::class,
        CiceroneModule::class,
        RepoModule::class,
        ApiModule::class,
        RepoRepoModul::class,
        CacheModule::class,
    UserDaoModul::class,
    RepoDaoModul::class]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(userPresenter: UserPresenter)
    fun inject(userFragment: UserFragment)
    fun inject(userCardFragment: UserCardFragment)
    fun inject(userCardPresenter: UserCardPresenter)
    fun inject(repoDetailsFragment: RepoDetailsFragment)
    fun inject(repoDetailsPresenter: RepoDetailsPresenter)

}