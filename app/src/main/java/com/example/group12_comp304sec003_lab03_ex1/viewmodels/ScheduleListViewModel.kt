package com.example.group12_comp304sec003_lab03_ex1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import com.example.group12_comp304sec003_lab03_ex1.database.schedule.AirlineSchedule
import com.example.group12_comp304sec003_lab03_ex1.database.schedule.ScheduleDao


class AirlineScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun fullSchedule(): Flow<List<AirlineSchedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<AirlineSchedule>> = scheduleDao.getByAirlineName(name)
}

class AirlineScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}