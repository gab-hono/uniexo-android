package com.unicofrance.uniexo.data.repositories

import com.unicofrance.uniexo.data.local.database.entities.Container
import com.unicofrance.uniexo.data.local.database.entities.ContainerDao

class ContainerRepository(private val containerDao: ContainerDao) {

    fun getAll() = containerDao.getAll()

    suspend fun insert(container: Container) = containerDao.insert(container)

    suspend fun insertAll(containers: List<Container>) = containerDao.insertAll(containers)

    suspend fun count() = containerDao.count()

    suspend fun deleteAll() = containerDao.deleteAll()
}