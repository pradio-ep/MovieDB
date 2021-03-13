package com.pradioep.test.ui.detail

import android.os.Bundle
import com.google.gson.Gson
import com.pradioep.test.BuildConfig
import com.pradioep.test.R
import com.pradioep.test.db.MovieDB
import com.pradioep.test.model.Movie
import com.pradioep.test.util.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity() {

    private val db: MovieDB by inject()

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setView()
    }

    private fun setView() {
        val movie = Gson().fromJson(intent.getStringExtra("Movie"), Movie::class.java)
        setToolbarAction(movie.title) {
            finish()
        }
        UtilityHelper.setImage(
            this,
            BuildConfig.IMAGE_URL+movie.poster_path,
            img_movie
        )
        tv_title.text = movie.title
        tv_relase_date.text = movie.release_date
        tv_overview.text = movie.overview
        img_favorite.setOnClickListener {
            GlobalScope.launch {
                if (isFavorite) {
                    db.dao().removeFavorite(movie)
                    isFavorite = false
                    this@DetailActivity.runOnUiThread {
                        it.setBackgroundResource(R.drawable.ic_heart)
                    }
                } else {
                    db.dao().addFavorite(movie)
                    isFavorite = true
                    this@DetailActivity.runOnUiThread {
                        it.setBackgroundResource(R.drawable.ic_heart_fill)
                    }
                }
            }
        }
        GlobalScope.launch {
            isFavorite = db.dao().isFavoriteMovie(movie.id)
            this@DetailActivity.runOnUiThread {
                if (isFavorite) {
                    img_favorite.setBackgroundResource(R.drawable.ic_heart_fill)
                } else {
                    img_favorite.setBackgroundResource(R.drawable.ic_heart)
                }
            }
        }
    }
}