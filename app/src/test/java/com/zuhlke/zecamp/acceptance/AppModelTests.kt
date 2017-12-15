package com.zuhlke.zecamp.acceptance

import com.zuhlke.zecamp.containers.Loadable
import com.zuhlke.zecamp.model.AppModel
import com.zuhlke.zecamp.networking.HttpClient
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.Request
import okhttp3.Response
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class TestHttpClient: HttpClient {
    override fun fetch(request: Request): Observable<Response> {
        return Observable.never()
    }

}

class AppModelTests {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            // Override the default AndroidSchedulers.mainThread() scheduler for tests.

            // This needs to be called before the test is initialized otherwise the
            // static block that initializes AndroidSchedulers is called first and will provide
            // a scheduler that depends on the Android SDK. This causes java.lang.ExceptionInInitializerError
            // during tests that exercise code that touches AndroidSchedulers.mainThread(),
            // including StateVariable.
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }
    }

    @Test
    fun modelScheduleStartsInLoadingState() {
        val initialSchedule = AppModel(TestHttpClient()).schedule.blockingFirst()
        assertTrue(initialSchedule is Loadable.Loading)
    }
}