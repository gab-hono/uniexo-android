package com.unicofrance.uniexo.data.repositories

import com.unicofrance.uniexo.data.local.database.entities.Container
import com.unicofrance.uniexo.data.local.database.entities.ContainerDao

class ContainerRepository(private val containerDao: ContainerDao) {

    fun getAll() = containerDao.getAll()

    suspend fun insert(container: Container) = containerDao.insert(container)

    suspend fun deleteAll() = containerDao.deleteAll()
}