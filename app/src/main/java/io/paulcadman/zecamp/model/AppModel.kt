package io.paulcadman.zecamp.model

import io.paulcadman.zecamp.containers.Loadable
import io.paulcadman.zecamp.extensions.asLoadable
import io.paulcadman.zecamp.extensions.deserialize
import io.paulcadman.zecamp.networking.HttpClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.HttpUrl
import okhttp3.Request
import java.util.concurrent.TimeUnit

class AppModel(httpClient: HttpClient) {
    val schedule: Observable<Loadable<ScheduleModel>>

    init {
        val request = Request.Builder().get().url(HttpUrl.parse("https://example.com")).build()
        schedule = httpClient
                .fetch(request)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    ScheduleModel(it.body()?.deserialize { typeFactory ->
                        typeFactory.constructCollectionType(List::class.java, EventModel::class.java)
                    } ?: emptyList())
                }
                .delay(3, TimeUnit.SECONDS)
                .asLoadable()
                .onErrorReturn {
                    Loadable.error(it)
                }
    }
}
