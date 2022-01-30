package com.codingwithmitch.daggerhiltplayground.repository

import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.retrofit.BlogRetrofit
import com.codingwithmitch.daggerhiltplayground.retrofit.NetworkMapper
import com.codingwithmitch.daggerhiltplayground.room.BlogDao
import com.codingwithmitch.daggerhiltplayground.room.CacheMapper
import com.codingwithmitch.daggerhiltplayground.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepository
@Inject constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> {

        val userFromRemote = blogRetrofit.get()
        updateDataBase(networkMapper.mapFromEntityList(userFromRemote))
        return blogDao.get()
            .map { cachedList ->
                val result = when {
                    cachedList.isEmpty() -> {
                        val usersFromRemote = blogRetrofit.get()
                        DataState.Success(networkMapper.mapFromEntityList(usersFromRemote))
                    }
                    else -> {
                        val usersFromCache = cacheMapper.mapFromEntityList(cachedList)
                        DataState.Success(usersFromCache)
                    }
                }
                result
            }
    }

    private suspend fun updateDataBase(blogs: List<Blog>) {
        blogs.forEach {
            blogDao.insert(cacheMapper.mapToEntity(it))
        }

    }

}