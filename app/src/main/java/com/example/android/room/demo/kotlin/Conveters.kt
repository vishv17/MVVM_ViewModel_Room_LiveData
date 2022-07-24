package com.example.android.room.demo.kotlin

import androidx.room.TypeConverter
import java.util.*

class Conveters {

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }
}