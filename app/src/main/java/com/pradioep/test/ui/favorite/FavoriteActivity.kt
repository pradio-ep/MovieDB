package com.pradioep.test.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradioep.test.R
import com.pradioep.test.adapter.FavoriteAdapter
import com.pradioep.test.db.MovieDB
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.ui.base.BaseActivity
import com.pradioep.test.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_category.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavoriteActivity : BaseActivity(), FavoriteAdapter.MovieListener {

    private val db: MovieDB by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }

    override fun onStart() {
        super.onStart()
        setToolbarAction("Favorite Movie") {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    private fun setView() {
        GlobalScope.launch {
            val listFavoriteMovie = db.dao().getListFavorite()
            if (listFavoriteMovie != null && listFavoriteMovie.isNotEmpty()) {
                this@FavoriteActivity.runOnUiThread {
                    setMovie(ArrayList(listFavoriteMovie))
                }
            }
        }
    }

    private fun setMovie(listMovieDetail: ArrayList<MovieDetail>) {
        rv_movie.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = FavoriteAdapter(context, listMovieDetail, this@FavoriteActivity).also {
                it.notifyDataSetChanged()
            }
        }
    }

    override fun onFavoriteClicked(movieDetail: MovieDetail) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie_id", movieDetail.id)
        startActivity(intent)
    }
}