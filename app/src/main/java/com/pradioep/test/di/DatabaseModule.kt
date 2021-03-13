package com.pradioep.test.di

import androidx.room.Room
import com.pradioep.test.db.MovieDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MovieDB::class.java, "movie_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}