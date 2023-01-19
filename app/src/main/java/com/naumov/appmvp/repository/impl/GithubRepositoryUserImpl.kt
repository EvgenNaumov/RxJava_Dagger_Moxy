package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.doCompletableIf
import com.naumov.appmvp.mapper.DboMapper
import com.naumov.appmvp.mapper.UserMapper
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubUserInterface
import com.naumov.appmvp.repository.INetworkStatus
import com.naumov.appmvp.repository.IRoomGithubUsersCache
import io.reactivex.rxjava3.core.Single

class GithubRepositoryUserImpl(
    private val userApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus:INetworkStatus,
    private val cacheUserDB: IRoomGithubUsersCache
) : GithubUserInterface {

    private val listUsers: List<GithubUserEntity> = listOf(
        GithubUserEntity(1,"Ivan", "some text about Ivan", ""),
        GithubUserEntity(2,"Mr. Jon", "some text about Mr. Jon", ""),
        GithubUserEntity(3,"Peter Pen", "some text about Peter Pen", ""),
        GithubUserEntity(4,"Klaus", "some text about Klaus", ""),
    )

    override fun getUsersDefault(): List<GithubUserEntity> {
        return listUsers
    }

    override fun getGithubUsers(): Single<List<GithubUserEntity>> {
        return networkStatus.statusSingle().flatMap { hasConnection->
            if (hasConnection){
                fetchFromApi(true)
            }else{
              getFromDb()
            }
        }
//        return fetchFromApi(true)
//        return userApi.getAllUsers().map { ListDTO -> ListDTO.map { UserMapper.mapToEntity(it) } }
    }

    private fun getFromDb():Single<List<GithubUserEntity>>{
        return Single.fromCallable{userDao.getAllUser()}.map{it.map(DboMapper::mapToUserEntity)}
    }

    override fun getUserWithRepos(login: String) {
//        return userDao.getUserWithRepos(login).map { userWithRepo ->
//            val user = DboMapper.mapToUserEntity(userWithRepo.userDBEntity)
//            user.repos = userWithRepo.repos.map { DboMapper.mapToRepoEntity(it) }
//            user
//        }
    }

    override fun getUserByid(login: String): Single<GithubUserEntity> {
        return userApi.getUser(login).map(UserMapper::mapToEntity)
    }


    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUserEntity>> {
        return userApi.getAllUsers()
            .doCompletableIf(shouldPersist){listUserDTO->
                cacheUserDB.saveUserCache(userDao,listUserDTO)
            }.map{it.map(UserMapper::mapToEntity)}
    }
}