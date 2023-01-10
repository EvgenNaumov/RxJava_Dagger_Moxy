package com.naumov.appmvp.mapper

import com.naumov.appmvp.database.RepoDBEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UserRepoDto

object RepoMapper {

    fun mapToRepoEntity(repoDto: UserRepoDto): UserRepoEntity {
        return UserRepoEntity(
            id = repoDto.id,
            login = repoDto.name,
            name = repoDto.name,
            forksUrl = repoDto.forksUrl,
            fullName = repoDto.fullName
        )
    }


    fun mapToRepoDB(repoDto: UserRepoDto):RepoDBEntity{
        return RepoDBEntity(
            id= repoDto.id,
            name = repoDto.name,
            forksUrl = repoDto.forksUrl,
            fullName = repoDto.fullName,
            login = repoDto.owner.login,
            userId = repoDto.owner.id
        )
    }

    fun mapFromDbToRepo(repoDb:RepoDBEntity):UserRepoEntity{
        return UserRepoEntity(
            id = repoDb.id,
            login = repoDb.name,
            name = repoDb.name,
            forksUrl = repoDb.forksUrl,
            fullName = repoDb.fullName
        )
    }

}