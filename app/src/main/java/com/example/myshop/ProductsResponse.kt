package com.example.myshop

class ProductsResponse (
    var products:List<Products>,
    var total: Int,
    var skip: Int,
    var limit: Int
)