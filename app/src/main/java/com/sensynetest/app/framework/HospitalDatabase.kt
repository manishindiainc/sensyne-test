package com.sensynetest.app.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sensynetest.app.domain.Hospital
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Hospital::class], version = 1)
abstract class HospitalDatabase : RoomDatabase() {

    abstract fun hospitalDao(): HospitalDao

    companion object {
        @Volatile
        private var INSTANCE: HospitalDatabase? = null

        fun getDatabase(
            context: Context
        ): HospitalDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HospitalDatabase::class.java,
                    "hospital_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}