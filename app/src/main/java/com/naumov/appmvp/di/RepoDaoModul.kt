package com.naumov.appmvp.di

import com.naumov.appmvp.database.ReposDAO
import dagger.Module
import dagger.Provides

@Module
class RepoDaoModul(val repoDao:ReposDAO) {

    @Provides
    fun repoDao():ReposDAO{
        return repoDao
    }
}