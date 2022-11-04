package com.example.ws_work_cars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ws_work_cars.data.local.entities.LeadDb
import retrofit2.http.GET


@Dao
interface LeadDao {

    /**
     * Salvar os leads no branco de dados
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLead(lead: LeadDb)

    @Query("SELECT * FROM leaddb")
    suspend fun getLeads(): List<LeadDb>

    /**
     * Limpar o banco de dados
     * */
    @Query("DELETE FROM LeadDb")
    suspend fun clearDb()

}