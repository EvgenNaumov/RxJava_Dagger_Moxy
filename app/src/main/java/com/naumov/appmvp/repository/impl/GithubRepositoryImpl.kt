package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubInterface
import io.reactivex.rxjava3.core.Observable
import java.util.*

class GithubRepositoryImpl: GithubInterface {

    private val listUsers:List<GithubUserEntity> = listOf(
        GithubUserEntity("Ivan","some text about Ivan"),
        GithubUserEntity("Mr. Jon","some text about Mr. Jon"),
        GithubUserEntity("Peter Pen","some text about Peter Pen"),
        GithubUserEntity("Klaus","some text about Klaus"),
    )

    override fun getGithubUsers():List<GithubUserEntity> {
        Thread.sleep(3000)
        return listUsers
    }

}