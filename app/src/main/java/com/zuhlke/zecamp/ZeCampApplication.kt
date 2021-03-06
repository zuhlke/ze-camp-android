package com.zuhlke.zecamp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zuhlke.zecamp.model.AppModel
import com.zuhlke.zecamp.networking.MockHttpClient

class ZeCampApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(ActivityCreationHandler { activity ->
            if (activity is MainActivity) {
                val httpClient = MockHttpClient()
                val model = AppModel(httpClient)
                activity.setUp(model)
            }
        })
    }
}

private class ActivityCreationHandler(val onCreate: (Activity) -> Unit) : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        activity?.let { onCreate(it) }
    }

    override fun onActivityPaused(p0: Activity?) {
    }

    override fun onActivityResumed(p0: Activity?) {
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivityDestroyed(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }
}
