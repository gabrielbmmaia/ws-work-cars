package com.example.ws_work_cars.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ws_work_cars.domain.model.Lead

/**
 * Modelo para ser adicionado ao banco de dados local
 * */

@Entity
data class LeadDb(

    @PrimaryKey(autoGenerate = true)
    val laedId: Long = 0,
    val carId: Long,
    val nomeLead: String,
    val emailLead: String

) {

    fun toLead(): Lead {
        return Lead(
            carId = carId,
            nomeLead = nomeLead,
            emailLead = emailLead
        )
    }
}
