package com.example.myshop

import kotlin.properties.Delegates

class Products(
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var ratings: Double,
    var stock: Int = 0,
    var category: String,
    var thumbnail: String
)



