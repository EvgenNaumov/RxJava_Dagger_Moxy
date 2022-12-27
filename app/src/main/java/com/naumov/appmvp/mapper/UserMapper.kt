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
            avatarUrl = userDto.avatarUrl
        )
    }


    fun mapToRepoEntity(userRepo: UserRepoDto, login:String): UserRepoEntity {
        return UserRepoEntity(
            id = userRepo.id,
            name = userRepo.name,
            forksUrl = userRepo.forksUrl,
            fullName = userRepo.fullName,
            login = login
        )
    }

    fun mapToDetailForkEntity(forks: ForkRepoDto): ForkRepoEntity {
        return ForkRepoEntity(
            id = forks.id,
            name = forks.name,
            description = forks.description,
            forkUrl = ""
        )
    }
}