package com.zuhlke.zecamp.ui.abstractions

import android.content.Context
import android.view.View

interface Screen {
    fun createView(context: Context): View
}