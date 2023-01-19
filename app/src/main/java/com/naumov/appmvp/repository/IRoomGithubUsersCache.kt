package com.naumov.appmvp.repository

import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.network.UserDto
import io.reactivex.rxjava3.core.Completable

interface IRoomGithubUsersCache {
    fun saveUserCache(userDao: UserDAO, listUserDTO:List<UserDto>):Completable
}