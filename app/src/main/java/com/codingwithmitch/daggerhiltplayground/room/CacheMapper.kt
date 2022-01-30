package com.codingwithmitch.daggerhiltplayground.room

import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModule: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModule.id,
            body = domainModule.body,
            title = domainModule.title,
            image = domainModule.image,
            category = domainModule.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>) = entities.map { mapFromEntity(it) }

}