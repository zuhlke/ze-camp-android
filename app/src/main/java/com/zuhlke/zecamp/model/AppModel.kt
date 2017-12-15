package com.zuhlke.zecamp.model

import com.zuhlke.zecamp.containers.Loadable
import com.zuhlke.zecamp.extensions.asLoadable
import com.zuhlke.zecamp.extensions.deserialize
import com.zuhlke.zecamp.networking.HttpClient
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
