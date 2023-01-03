package com.naumov.appmvp.mapper

import com.naumov.appmvp.database.UserDBEntity
import com.naumov.appmvp.network.UserDto

object DboMapper {

    fun mapToDboEntity(dto:UserDto):UserDBEntity{
        return UserDBEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }
}