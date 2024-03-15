package com.example.group12_comp304sec003_lab03_ex1

import android.app.Application
import com.example.group12_comp304sec003_lab03_ex1.database.AirlineDatabase

class AirlineScheduleApplication : Application() {
    val database: AirlineDatabase by lazy { AirlineDatabase.getDatabase(this) }
}