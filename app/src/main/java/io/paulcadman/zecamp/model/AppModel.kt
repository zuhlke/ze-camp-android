package io.paulcadman.zecamp.model

import io.paulcadman.zecamp.containers.Loadable
import io.paulcadman.zecamp.extensions.asLoadable
import io.paulcadman.zecamp.extensions.deserialize
import io.paulcadman.zecamp.networking.HttpClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.HttpUrl
import okhttp3.Request

class AppModel(httpClient: HttpClient) {
    val schedule: Observable<Loadable<ScheduleModel>>

    init {
        val request = Request.Builder().get().url(HttpUrl.parse("https://api.coinmarketcap.com/v1/ticker/?limit=10000")).build()
        schedule = httpClient
                .fetch(request)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    ScheduleModel(it.body()?.deserialize { typeFactory ->
                        typeFactory.constructCollectionType(List::class.java, EventModel::class.java)
                    } ?: emptyList())
                }
                .asLoadable()
                .onErrorReturn {
                    Loadable.error(it)
                }
    }
}
