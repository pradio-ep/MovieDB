package com.pradioep.test.ui.main

import android.os.Bundle
import com.pradioep.test.R
import com.pradioep.test.helper.UtilityHelper
import com.pradioep.test.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by inject()

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
        }

        setView()
    }

    private fun setView() {
        viewModel.getPopular()
    }
}