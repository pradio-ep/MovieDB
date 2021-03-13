package com.pradioep.test.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import com.pradioep.test.R
import kotlinx.android.synthetic.main.toolbar.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }

    fun setToolbar(title: String, favoriteListener: View.OnClickListener) {
        toolbar_title.text = title
        toolbar_title.setPadding(50, 0, 0, 0)
        toolbar_back.visibility = View.GONE
        toolbar_favorite.setOnClickListener(favoriteListener)
    }

    fun setToolbarAction(title: String, backListener: View.OnClickListener) {
        toolbar_title.text = title
        toolbar_title.setPadding(0, 0, 25, 0)
        toolbar_back.setOnClickListener(backListener)
        toolbar_favorite.visibility = View.INVISIBLE
    }

    private fun initDialog() {
        loadingDialog = Dialog(this)
        loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog.setContentView(R.layout.dialog_waiting)
        loadingDialog.setCancelable(false)
    }

    fun showWaitingDialog() {
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
        }
    }

    fun hideWaitingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}