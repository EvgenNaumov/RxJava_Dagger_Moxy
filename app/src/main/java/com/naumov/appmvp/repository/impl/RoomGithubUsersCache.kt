package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.mapper.DboMapper
import com.naumov.appmvp.network.UserDto
import com.naumov.appmvp.repository.IRoomGithubUsersCache
import io.reactivex.rxjava3.core.Completable

class RoomGithubUsersCache:IRoomGithubUsersCache {
    override fun saveUserCache(userDao:UserDAO, listUserDTO:List<UserDto>): Completable {
        return Completable.create{
            userDao.insertAll(listUserDTO.map(DboMapper::mapToDboEntity))
            it.onComplete()
        }
    }
}