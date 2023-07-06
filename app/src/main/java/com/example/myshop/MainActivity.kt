package com.example.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.productRv
        recyclerView.layoutManager = GridLayoutManager(this,2)
        productAdapter = ProductAdapt(mutableListOf())
        recyclerView.adapter = productAdapter
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }

    private fun getProducts() {
        val apiClient = ApiClient.buildClient(ApiInterface::class.java)
        val request = apiClient.getProduct()

        request.enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {
                    val products = response.body()?.products
                    if (products != null) {
                        productAdapter.updateProducts(products)
                        Toast.makeText(
                            baseContext,
                            "Fetched ${products.size} products",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(baseContext, "No products found", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        baseContext,
                        "Error: ${response.code()} ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(baseContext, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}




