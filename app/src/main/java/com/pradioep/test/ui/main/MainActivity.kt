package com.pradioep.test.ui.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.pradioep.test.R
import com.pradioep.test.adapter.MovieAdapter
import com.pradioep.test.model.Movie
import com.pradioep.test.util.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import com.pradioep.test.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_category.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), MovieAdapter.MovieListener {

    private val viewModel: MainViewModel by inject()

    private var category = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewModel) {
            isLoading.observe(this@MainActivity, { bool ->
                bool.let { loading ->
                    if(loading){ showWaitingDialog() }
                    else { hideWaitingDialog() }
                }
            })
            networkError.observe(this@MainActivity, {
                UtilityHelper.showSnackBar(view_parent, it)
            })
            serverError.observe(this@MainActivity, {
                UtilityHelper.showSnackBar(view_parent, it)
            })
            listMovie.observe(this@MainActivity, {
                setMovie(it)
            })
        }

        setView()
    }

    override fun onStart() {
        super.onStart()
        setToolbar(getString(R.string.app_name)) {
            startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    private fun setView() {
        setCategory(category)
        tv_category.setOnClickListener {
            showDialogCategory()
        }
    }

    private fun setCategory(category: Int) {
        viewModel.getCategory(category)
    }

    private fun setMovie(listMovie: ArrayList<Movie>) {
        rv_movie.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(context, listMovie, this@MainActivity).also {
                it.notifyDataSetChanged()
            }
        }
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("Movie", Gson().toJson(movie))
        startActivity(intent)
    }

    private fun showDialogCategory() {
        BottomSheetDialog(this).apply {
            setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_category,null))
            setCancelable(true)
        }.also {
            when (category) {
                1 -> {
                    it.tv_popular.setTypeface(null, Typeface.BOLD)
                }
                2 -> {
                    it.tv_upcoming.setTypeface(null, Typeface.BOLD)
                }
                3 -> {
                    it.tv_top_rated.setTypeface(null, Typeface.BOLD)
                }
                else -> {
                    it.tv_now_playing.setTypeface(null, Typeface.BOLD)
                }
            }
            it.tv_popular.setOnClickListener { _ ->
                category = 1
                setCategory(category)
                tv_category.text = "Popular"
                it.dismiss()
            }
            it.tv_upcoming.setOnClickListener { _ ->
                category = 2
                setCategory(category)
                tv_category.text = "Upcoming"
                it.dismiss()
            }
            it.tv_top_rated.setOnClickListener { _ ->
                category = 3
                setCategory(category)
                tv_category.text = "Top Rated"
                it.dismiss()
            }
            it.tv_now_playing.setOnClickListener { _ ->
                category = 4
                setCategory(category)
                tv_category.text = "Now Playing"
                it.dismiss()
            }
            it.show()
        }
    }
}