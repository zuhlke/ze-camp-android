package io.paulcadman.zecamp.ui

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import io.paulcadman.zecamp.model.ScheduleModel
import io.paulcadman.zecamp.ui.abstractions.Screen

class ScheduleScreen(val scheduleModel: ScheduleModel): Screen {
    override fun createView(context: Context): View {
        val scrollView = ScrollView(context)
        scrollView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        val textView = TextView(context)
        textView.text = scheduleModel.events.map { it.name }.joinToString("\n")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.RIGHT
        layout.addView(textView)

        scrollView.addView(layout)
        return scrollView
    }
}