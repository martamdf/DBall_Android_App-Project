package com.example.basicapp.utils

import com.google.common.io.Resources
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit


class DragonBallApiMockDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/api/heros/all" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/heroes.json"))
            }
            "/api/heros/locations" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/locations.json"))
            }
            "/api/data/herolike" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_CREATED)
                    .setBody("")
            }
            else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }
}

internal fun getJson(path: String): String {

    val file = File("/Users/martamaquedano/Projects/10. Android Avanzado/BasicApp/app/src/main/resources/" +path)
    return String(file.readBytes())
}