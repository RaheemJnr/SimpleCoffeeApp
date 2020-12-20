package com.example.coffeeapp.screens.order

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.database.CoffeeDAO

class OrderViewModelFactory(
    private val dataSource: CoffeeDAO,
    private val application: Application,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}