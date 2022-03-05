package com.bakigoal.routes

import com.bakigoal.module
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class OrderRouteTests {
    @Test
    fun testGetOrder() {
        val item1 = """{"item":"Ham Sandwich","amount":2,"price":5.5}"""
        val item2 = """{"item":"Water","amount":1,"price":1.5}"""
        val item3 = """{"item":"Beer","amount":3,"price":2.3}"""
        val item4 = """{"item":"Cheesecake","amount":1,"price":3.75}"""

        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2022-03-06-01").apply {
                assertEquals(
                    """{"number":"2022-03-06-01","contents":[$item1,$item2,$item3,$item4]}""".trimIndent(),
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}