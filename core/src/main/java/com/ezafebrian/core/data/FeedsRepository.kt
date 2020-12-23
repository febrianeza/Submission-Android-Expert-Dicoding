package com.ezafebrian.core.data

import com.ezafebrian.core.data.source.local.LocalDataSource
import com.ezafebrian.core.data.source.remote.ApiResponse
import com.ezafebrian.core.data.source.remote.RemoteDataSource
import com.ezafebrian.core.data.source.remote.response.PostsResponse
import com.ezafebrian.core.domain.model.Feeds
import com.ezafebrian.core.domain.repository.IFeedsRepository
import com.ezafebrian.core.utils.AppExecutors
import com.ezafebrian.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeedsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IFeedsRepository {
    override fun getPosts(): Flow<Resource<List<Feeds>>> =
        object : NetworkBoundResource<List<Feeds>, PostsResponse>() {
            override fun loadFromDB(): Flow<List<Feeds>> {
                return localDataSource.getPosts().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Feeds>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<PostsResponse>> {
                return remoteDataSource.getPosts()
            }

            override suspend fun saveCallResult(data: PostsResponse) {
                val feedsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertPosts(feedsList)
            }
        }.asFlow()

    override fun getLikedPost(): Flow<List<Feeds>> =
        localDataSource.getLikedPosts().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setLikePost(feeds: Feeds, state: Boolean) {
        val feedsEntity = DataMapper.mapDomainToEntity(feeds)
        appExecutors.diskIO().execute {
            localDataSource.updateLikePost(feedsEntity, state)
        }
    }
}