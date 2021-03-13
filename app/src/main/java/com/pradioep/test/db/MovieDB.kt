package com.pradioep.test.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pradioep.test.model.Movie

@Database(version = 1, exportSchema = false, entities = [Movie::class])
@TypeConverters(Converters::class)
abstract class MovieDB : RoomDatabase() {
    abstract fun dao(): Dao
}