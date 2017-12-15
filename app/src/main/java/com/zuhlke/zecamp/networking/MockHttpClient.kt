package com.zuhlke.zecamp.networking

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.zuhlke.zecamp.model.EventModel
import io.reactivex.Observable
import okhttp3.*

class MockHttpClient: HttpClient {
    override fun fetch(request: Request): Observable<Response> {
        val events = listOf<EventModel>(
                EventModel("First"),
                EventModel("Second"),
                EventModel("Third")
                )

        val responseBody = ResponseBody.create(MediaType.parse("application/json"), jacksonObjectMapper().writeValueAsString(events))
        return Observable.just(Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody).build())
    }
}