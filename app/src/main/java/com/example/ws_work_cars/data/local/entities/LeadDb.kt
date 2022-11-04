package com.example.ws_work_cars.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LeadDb(

    @PrimaryKey(autoGenerate = true)
    val laedId: Long = 0,
    val carId: Long,
    val nomeLead: String,
    val emailLead: String

)
