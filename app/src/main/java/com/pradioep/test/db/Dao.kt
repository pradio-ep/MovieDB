package com.pradioep.test.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradioep.test.model.Movie

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(movie: Movie)

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :id)")
    fun isFavoriteMovie(id : Int) : Boolean

    @Query("SELECT * FROM Movie")
    fun getListFavorite(): List<Movie>?

    @Delete
    fun removeFavorite(movie: Movie)
}