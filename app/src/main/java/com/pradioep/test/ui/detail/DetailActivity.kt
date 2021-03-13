package com.pradioep.test.ui.detail

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.pradioep.test.BuildConfig
import com.pradioep.test.R
import com.pradioep.test.model.Movie
import com.pradioep.test.util.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity() {

    private val viewModel: DetailViewModel by inject()

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
        }

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
    }
}