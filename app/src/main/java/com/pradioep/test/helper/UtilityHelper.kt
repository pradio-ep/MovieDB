package com.pradioep.test.helper

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.pradioep.test.R

class UtilityHelper {

    companion object {

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