package com.pradioep.test.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.model.MovieItem

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(movieDetail: MovieDetail)

    @Query("SELECT EXISTS(SELECT * FROM MovieDetail WHERE id = :id)")
    fun isFavoriteMovie(id : Int) : Boolean

    @Query("SELECT * FROM MovieDetail")
    fun getListFavorite(): List<MovieDetail>?

    @Delete
    fun removeFavorite(movieItem: MovieDetail)
}