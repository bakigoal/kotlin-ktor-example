package com.bakigoal.repo

import com.bakigoal.models.Customer
import com.bakigoal.models.Order
import com.bakigoal.models.OrderItem

val customerStorage = mutableListOf<Customer>()

val orderStorage = listOf(
    Order(
        "2022-03-06-01", listOf(
            OrderItem("Ham Sandwich", 2, 5.50),
            OrderItem("Water", 1, 1.50),
            OrderItem("Beer", 3, 2.30),
            OrderItem("Cheesecake", 1, 3.75)
        )
    ),
    Order(
        "2022-03-03-01", listOf(
            OrderItem("Cheeseburger", 1, 8.50),
            OrderItem("Water", 2, 1.50),
            OrderItem("Coke", 2, 1.76),
            OrderItem("Ice Cream", 1, 2.35)
        )
    )
)