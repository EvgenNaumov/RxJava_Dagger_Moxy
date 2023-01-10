package com.naumov.appmvp.mapper

import com.naumov.appmvp.database.RepoDBEntity
import com.naumov.appmvp.database.UserDBEntity
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UserDto

object DboMapper {

    fun mapToDboEntity(dto: UserDto): UserDBEntity {
        return UserDBEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl
        )
    }

    fun mapToUserEntity(dboUser: UserDBEntity): GithubUserEntity {
        return GithubUserEntity(
            login = dboUser.login,
            avatarUrl = dboUser.avatarUrl,
            id = dboUser.id,
            description = ""
        )
    }

}