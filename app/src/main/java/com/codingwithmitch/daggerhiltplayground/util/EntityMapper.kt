package com.codingwithmitch.daggerhiltplayground.util

interface EntityMapper<Entity, DomainModule> {

    fun mapFromEntity(entity: Entity): DomainModule

    fun mapToEntity(domainModule: DomainModule): Entity
}