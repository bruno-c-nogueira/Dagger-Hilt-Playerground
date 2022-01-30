package com.codingwithmitch.daggerhiltplayground.retrofit

import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModule: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModule.id,
            title = domainModule.title,
            body = domainModule.body,
            image = domainModule.image,
            category = domainModule.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>) = entities.map { mapFromEntity(it) }

}