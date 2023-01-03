package com.naumov.appmvp.mapper

import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UserRepoDto

object RepoMapper {

    fun mapToRepoEntity(userRepo: UserRepoDto, login:String): UserRepoEntity {
        return UserRepoEntity(
            id = userRepo.id,
            name = userRepo.name,
            forksUrl = userRepo.forksUrl,
            fullName = userRepo.fullName,
            login = login
        )
    }

}