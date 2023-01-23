package com.naumov.appmvp.di

import com.naumov.appmvp.database.UserDAO
import dagger.Module
import dagger.Provides

@Module
class UserDaoModul(val userDao:UserDAO) {

   @Provides
   fun userDao():UserDAO{
       return userDao
   }
}