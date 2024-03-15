package com.example.group12_comp304sec003_lab03_ex1.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AirlineSchedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "airline_name") val airlineName: String,

    @NonNull @ColumnInfo(name = "terminal_number") val terminalNumber: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
    @NonNull @ColumnInfo(name = "status") val status: String
)
