package com.naumov.appmvp.mapper

import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.network.ForkRepoDto

object ForkMapper {

    fun mapToDetailForkEntity(forks: ForkRepoDto): ForkRepoEntity {
        return ForkRepoEntity(
            id = forks.id,
            name = forks.name,
            description = forks.description,
            forkUrl = ""
        )
    }
}