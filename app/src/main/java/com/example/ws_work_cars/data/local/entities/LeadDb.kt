package com.example.ws_work_cars.data.local.entities

import androidx.room.Entity

@Entity
data class LeadDb(
    val carId: Long,
    val nomeLead: String,
    val emailLead: String
)
