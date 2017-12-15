package io.paulcadman.zecamp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import io.paulcadman.zecamp.model.AppModel
import io.paulcadman.zecamp.ui.AppLoadingScreen
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.AppCompatImageView
import android.view.ViewGroup
import android.widget.FrameLayout
import io.paulcadman.zecamp.extensions.latestPair

class MainActivity : AppCompatActivity() {

    fun setUp(model: AppModel) {
        val rootScreen = model.schedule.map { scheduleLoadable ->
            AppLoadingScreen()
        }

        val context = this.applicationContext
        val root = CoordinatorLayout(this)
        val container = FrameLayout(context)
        val backgroundView = AppCompatImageView(context)
        backgroundView.setBackgroundColor(Color.RED)

        container.fitsSystemWindows = true
        root.addView(backgroundView, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        root.addView(container, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        rootScreen.map { it.createView(context)}.latestPair().subscribe { view ->
            view.old?.let {
                container.removeView(it)
            }

            container.addView(view.new, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }
        setContentView(root)
    }
}
