package com.example.group12_comp304sec003_lab03_ex1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.group12_comp304sec003_lab03_ex1.database.schedule.AirlineSchedule
import com.example.group12_comp304sec003_lab03_ex1.database.schedule.ScheduleDao
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory


@Database(entities = arrayOf(AirlineSchedule::class), version = 1)
abstract class AirlineDatabase : RoomDatabase(){
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AirlineDatabase? = null

        fun getDatabase(context: Context): AirlineDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseFile = context.getDatabasePath("airline_database.db")
                val factory = SupportSQLiteOpenHelper.Configuration.builder(context)
                    .name("airline_database.db")
                    .callback(object : SupportSQLiteOpenHelper.Callback(1) {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            // Handle creation of the database if needed
                        }

                        override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
                            // Handle database upgrade if needed
                        }
                    })
                    .build()

                val configuration = SupportSQLiteOpenHelper.Configuration.builder(context)
                    .name(databaseFile.absolutePath)
                    .callback(factory.callback)
                    .build()

                val openHelper = FrameworkSQLiteOpenHelperFactory().create(configuration)

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AirlineDatabase::class.java,
                    "airline_database"
                )
                    .openHelperFactory { openHelper }
                    .build()

                INSTANCE = instance

                instance
            }
        }

    }

}