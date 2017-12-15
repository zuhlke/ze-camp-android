package io.paulcadman.zecamp.extensions

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

import okhttp3.ResponseBody

fun <T> ResponseBody.deserialize(constructType: (TypeFactory) -> (JavaType)): T {
    byteStream().use {
        it.bufferedReader().use {
            val mapper = jacksonObjectMapper()
            return mapper.readValue(it, constructType(mapper.typeFactory))
        }
    }
}
