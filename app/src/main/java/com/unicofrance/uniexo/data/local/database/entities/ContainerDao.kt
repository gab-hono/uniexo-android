package com.unicofrance.uniexo.data.local.database.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContainerDao {
    @Query("SELECT * FROM container")
    fun getAll(): Flow<List<Container>>

    @Insert
    suspend fun insert(container: Container)

    @Insert
    suspend fun insertAll(containers: List<Container>)

    @Query("SELECT COUNT(*) FROM container")
    suspend fun count(): Int

    @Query("""DELETE FROM container""")
    suspend fun deleteAll()
}