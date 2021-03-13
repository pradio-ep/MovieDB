package com.pradioep.test.ui.favorite

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.pradioep.test.R
import com.pradioep.test.adapter.MovieAdapter
import com.pradioep.test.db.MovieDB
import com.pradioep.test.model.Movie
import com.pradioep.test.util.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import com.pradioep.test.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_category.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavoriteActivity : BaseActivity(), MovieAdapter.MovieListener {

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

    private fun setMovie(listMovie: ArrayList<Movie>) {
        rv_movie.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(context, listMovie, this@FavoriteActivity).also {
                it.notifyDataSetChanged()
            }
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("Movie", Gson().toJson(movie))
        startActivity(intent)
    }
}