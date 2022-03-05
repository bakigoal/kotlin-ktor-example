package com.bakigoal.client

import com.bakigoal.models.Customer
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.io.File

val client = HttpClient(Jetty) {
    install(JsonFeature){
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
}

/**
 * After you finish working with the HTTP client,
 * you need to free up the resources: threads, connections, and CoroutineScope for coroutines
 */
fun close() = client.close()

/**
 * If you need to use HttpClient for a single request, call the use function
 * HttpClient().use
 */

suspend fun request() {
    val response: HttpResponse = client.request("https://ktor.io/") {
        // Configure request parameters exposed by HttpRequestBuilder
        method = HttpMethod.Get
    }
}

suspend fun get() {
    val response: HttpResponse = client.get("https://ktor.io/")
}

suspend fun deserializeWithJsonPlugin() {
    val customer: Customer = client.get("http://localhost:8080/customer/3")
}

suspend fun headers() {
    val response: HttpResponse = client.get("https://ktor.io/") {
        headers {
            append(HttpHeaders.Accept, "text/html")
            append(HttpHeaders.Authorization, "Bearer token")
            append(HttpHeaders.UserAgent, "ktor client")
        }
    }
}

suspend fun params() {
    val response: HttpResponse = client.get("http://localhost:8080/products") {
        parameter("price", "asc")
    }
}

suspend fun body() {
    val response: HttpResponse = client.get("http://localhost:8080/post") {
        body = "Body content"
    }
}

suspend fun objects() {
    val response: HttpResponse = client.get("http://localhost:8080/customer") {
        contentType(ContentType.Application.Json)
        body = Customer("3", "Baki", "Goal", "baki@goal.com")
    }
}

suspend fun formParams() {
    val response: HttpResponse = client.submitForm(
        url = "http://localhost:8080/get",
        formParameters = Parameters.build {
            append("first_name", "Baki")
            append("last_name", "Goal")
        },
        encodeInQuery = true
    )
}

suspend fun uploadFile() {
    val response: HttpResponse = client.submitFormWithBinaryData(
        url = "http://localhost:8080/upload",
        formData = formData {
            append("description", "Ktor logo")
            append("image", File("ktor_logo.png").readBytes(), Headers.build {
                append(HttpHeaders.ContentType, "image/png")
                append(HttpHeaders.ContentDisposition, "filename=ktor_logo.png")
            })
        }
    ) {
        onUpload { bytesSentTotal, contentLength ->
            println("Sent $bytesSentTotal bytes from $contentLength")
        }
    }
}