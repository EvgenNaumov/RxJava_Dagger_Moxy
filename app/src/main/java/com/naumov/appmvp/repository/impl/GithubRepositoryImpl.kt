package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.doCompletableIf
import com.naumov.appmvp.mapper.DboMapper
import com.naumov.appmvp.mapper.ForkMapper
import com.naumov.appmvp.mapper.RepoMapper
import com.naumov.appmvp.mapper.UserMapper
import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl(
    private val userApi: UsersApi,
    private val userDao: UserDAO,
    private val networkStatus:Single<Boolean>
) : GithubInterface {

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
        return networkStatus.flatMap { hasConnection->
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
        return userDao.getAllUser().map { it.map(DboMapper::mapToUserEntity) }
    }

    override fun getUserWithRepos(login: String): Single<GithubUserEntity> {
        return userDao.getUserWithRepos(login).map { userWithRepo ->
            val user = DboMapper.mapToUserEntity(userWithRepo.userDBEntity)
            user.repos = userWithRepo.repos.map { DboMapper.mapToRepoEntity(it) }
            user
        }
    }

    override fun getUserByid(login: String): Single<GithubUserEntity> {
        return userApi.getUser(login).map(UserMapper::mapToEntity)
    }

    override fun getUserRepoById(login: String): Single<List<UserRepoEntity>> {
        return userApi.getRepos(login)
            .map { it -> it.map { RepoMapper.mapToRepoEntity(it, login) } }
    }

    override fun getForksRepoById(login: String, nameRepo: String): Single<List<ForkRepoEntity>> {
        return userApi.getForks(login, nameRepo).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    override fun getForksByUrl(forkUrl: String): Single<List<ForkRepoEntity>> {
        return userApi.getForksByUrl(forkUrl).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUserEntity>> {
        return userApi.getAllUsers()
            .doCompletableIf(shouldPersist){
                userDao.inserAll(it.map(DboMapper::mapToDboEntity))
            }
            .map{it.map(UserMapper::mapToEntity)}
    }
}