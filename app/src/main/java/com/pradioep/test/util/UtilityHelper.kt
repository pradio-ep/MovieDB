package com.pradioep.test.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pradioep.test.R
import java.lang.Exception

class UtilityHelper {

    companion object {

        fun setImage(context: Context, content: Any, img: ImageView) {
            try {
                Glide.with(context)
                        .load(content)
                        .placeholder(R.color.white)
                        .into(img)
            } catch (e: Exception) {
                img.setBackgroundResource(R.color.white)
            }
        }

        fun showSnackBar(view: View, text: String) {
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction("OK") { }

            val viewSnackbar = snackbar.view
            val textView = viewSnackbar.findViewById(R.id.snackbar_text) as TextView
            textView.maxLines = 5
            textView.setPadding(0, 0, 0, 0)

            snackbar.show()
        }
    }
}