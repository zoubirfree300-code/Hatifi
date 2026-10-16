package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {

    @Query("SELECT * FROM phones ORDER BY year DESC, priceUsd DESC")
    fun getAllPhonesFlow(): Flow<List<PhoneEntity>>

    @Query("SELECT * FROM phones WHERE id = :id LIMIT 1")
    fun getPhoneByIdFlow(id: String): Flow<PhoneEntity?>

    @Query("SELECT * FROM phones WHERE id = :id LIMIT 1")
    suspend fun getPhoneById(id: String): PhoneEntity?

    @Query("SELECT * FROM phones WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoritePhonesFlow(): Flow<List<PhoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(phones: List<PhoneEntity>)

    @Query("UPDATE phones SET isFavorite = :isFav WHERE id = :phoneId")
    suspend fun setFavorite(phoneId: String, isFav: Boolean)

    @Query("SELECT COUNT(*) FROM phones")
    suspend fun getCount(): Int

    @Query("DELETE FROM phones")
    suspend fun clearAll()
}
