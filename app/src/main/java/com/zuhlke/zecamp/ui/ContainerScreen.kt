package com.zuhlke.zecamp.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.view.View
import android.view.ViewGroup
import com.zuhlke.zecamp.ui.abstractions.Screen
import io.reactivex.Observable

class ContainerScreen(val content: Observable<Screen>): Screen {
    override fun createView(context: Context): View {
        val container = LinearLayoutCompat(context)
        container.orientation = LinearLayoutCompat.VERTICAL

        content.subscribe { screen ->
            container.removeAllViews()
            val layout = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            container.addView(screen.createView(context), layout)
        }

        return container
    }
}