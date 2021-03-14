package com.pradioep.test.ui.detail

import android.os.Bundle
import android.widget.Toast
import com.pradioep.test.BuildConfig
import com.pradioep.test.R
import com.pradioep.test.db.MovieDB
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.util.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by inject()
    private val db: MovieDB by inject()

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        with(viewModel) {
            isLoading.observe(this@DetailActivity, { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            networkError.observe(this@DetailActivity, {
                Toast.makeText(this@DetailActivity, it, Toast.LENGTH_SHORT).show()
                finish()
            })
            serverError.observe(this@DetailActivity, {
                Toast.makeText(this@DetailActivity, it, Toast.LENGTH_SHORT).show()
                finish()
            })
            movieDetail.observe(this@DetailActivity, {
                setMovieDetail(it)
            })
        }

        setView()
    }

    private fun setView() {
        viewModel.getMovieDetail(intent.getIntExtra("movie_id", 0))
    }

    private fun setMovieDetail(movie: MovieDetail) {
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