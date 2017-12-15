package com.zuhlke.zecamp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import com.zuhlke.zecamp.model.AppModel
import com.zuhlke.zecamp.ui.AppLoadingScreen
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.AppCompatImageView
import android.view.ViewGroup
import android.widget.FrameLayout
import com.zuhlke.zecamp.containers.Loadable
import com.zuhlke.zecamp.extensions.latestPair
import com.zuhlke.zecamp.ui.ScheduleScreen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null

    fun setUp(model: AppModel) {
        val rootScreen = model.schedule.map { schedule ->
            when (schedule) {
                is Loadable.Loading -> AppLoadingScreen()
                is Loadable.Loaded -> ScheduleScreen(schedule.result)
                is Loadable.Error -> AppLoadingScreen()
            }
        }.observeOn(AndroidSchedulers.mainThread())

        val context = this.applicationContext
        val root = CoordinatorLayout(this)
        val container = FrameLayout(context)
        val backgroundView = AppCompatImageView(context)
        backgroundView.setBackgroundColor(Color.RED)

        container.fitsSystemWindows = true
        root.addView(backgroundView, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        root.addView(container, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        disposable = rootScreen.map { it.createView(context)}.latestPair().subscribe { view ->
            view.old?.let {
                container.removeView(it)
            }
            container.addView(view.new, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }

        setContentView(root)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
