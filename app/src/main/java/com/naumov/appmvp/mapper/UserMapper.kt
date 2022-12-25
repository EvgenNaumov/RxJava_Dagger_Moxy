package com.naumov.appmvp.mapper

import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.network.UserDto

object UserMapper {

    fun mapToEntity(userDto: UserDto):GithubUserEntity{
        return GithubUserEntity(
            login = userDto.login,
            description = userDto.description,
            avatarUrl = userDto.avatarUrl
        )
    }


}