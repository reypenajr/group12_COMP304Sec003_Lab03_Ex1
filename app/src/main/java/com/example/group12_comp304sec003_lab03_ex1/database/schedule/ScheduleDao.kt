package com.example.group12_comp304sec003_lab03_ex1.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM airlineschedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<AirlineSchedule>>

    @Query("SELECT * FROM airlineschedule WHERE airline_name = :airlineName ORDER BY arrival_time ASC")
    fun getByAirlineName(airlineName: String): Flow<List<AirlineSchedule>>

}
