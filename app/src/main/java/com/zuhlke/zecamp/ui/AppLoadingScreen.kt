package com.zuhlke.zecamp.ui

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import com.zuhlke.zecamp.ui.abstractions.Screen

class AppLoadingScreen: Screen {
    override fun createView(context: Context): View {
        return ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
    }
}