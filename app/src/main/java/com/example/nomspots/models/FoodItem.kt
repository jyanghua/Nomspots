package com.example.nomspots.models

class FoodItem {
    var name: String? = null
    var restaurant: String? = null
    var price: Double? = null
    var image: String? = null

    // Empty parameter constructor
    constructor() {}

    // Default constructor
    constructor(
        name: String?,
        restaurant: String?,
        price: Double?,
        image: String
    ) {
        this.name = name
        this.restaurant = restaurant
        this.price = price
        this.image = image
    }

}