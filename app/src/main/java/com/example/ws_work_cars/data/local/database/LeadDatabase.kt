package com.example.ws_work_cars.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ws_work_cars.data.local.dao.LeadDao
import com.example.ws_work_cars.data.local.entities.LeadDb


@Database(
    entities = [LeadDb::class],
    version = 1,
    exportSchema = false
)
abstract class LeadDatabase : RoomDatabase() {

    abstract val dao: LeadDao

    /**
     * Aqui estamos criando uma instancia do database caso ela ainda não exista
     * e caso exista estamos pegando a mesma referência
     * */

    companion object {

        @Volatile
        private var INSTANCE: LeadDatabase? = null

        fun getInstance(context: Context): LeadDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LeadDatabase::class.java,
                        "leads_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}