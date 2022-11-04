package com.example.ws_work_cars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LeadDao {

    /**
     * Salvar os leads no branco de dados
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLead(lead: LeadDao)

    /**
     * Limpar o banco de dados
     * */
    @Query("DELETE FROM leads")
    suspend fun clearDb()

}