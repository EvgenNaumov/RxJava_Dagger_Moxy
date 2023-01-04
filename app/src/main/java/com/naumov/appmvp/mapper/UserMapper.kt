package com.naumov.appmvp.mapper

import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.ForkRepoDto
import com.naumov.appmvp.network.UserDto
import com.naumov.appmvp.network.UserRepoDto

object UserMapper {

    fun mapToEntity(userDto: UserDto): GithubUserEntity {
        return GithubUserEntity(
            login = userDto.login,
            description = userDto.description,
            avatarUrl = userDto.avatarUrl,
            id = userDto.id
        )
    }

}