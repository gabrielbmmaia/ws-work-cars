package com.example.ws_work_cars.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ws_work_cars.data.local.entities.LeadDb


@Dao
interface LeadDao {

    /**
     * Salvar os leads no branco de dados local
     * */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLead(lead: LeadDb)

    /**
     * Pega todos os leads do banco de dados local
     * */

    @Query("SELECT * FROM leaddb")
    suspend fun getLeads(): List<LeadDb>

    /**
     * Limpar o banco de dados local
     * */

    @Query("DELETE FROM LeadDb")
    suspend fun clearDb()

}