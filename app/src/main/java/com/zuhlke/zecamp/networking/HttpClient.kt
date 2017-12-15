package com.zuhlke.zecamp.networking

import io.reactivex.Observable
import okhttp3.*
import java.io.IOException

interface HttpClient {
    fun fetch(request: Request): Observable<Response>
}

class OKHttpClientFetcher(val client: OkHttpClient): HttpClient {
    override fun fetch(request: Request): Observable<Response> {
        val call = client.newCall(request)

        return Observable.create<Response> { subscriber ->
            call.enqueue(object: Callback {
                override fun onFailure(call: Call?, e: IOException) {
                    subscriber.onError(e)
                }

                override fun onResponse(call: Call?, response: Response) {
                    subscriber.onNext(response)
                    subscriber.onComplete()
                }

            })
        }.doOnDispose(call::cancel).share()
    }

}