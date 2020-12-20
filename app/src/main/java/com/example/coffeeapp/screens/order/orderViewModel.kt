package com.example.coffeeapp.screens.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.database.CoffeeDAO
import com.example.coffeeapp.database.CoffeeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderViewModel(private val database: CoffeeDAO, app: Application) : AndroidViewModel(app) {

    var quantities: Int = 1

    //increase order function
    fun increaseButton(): Int {
        while (quantities <= 9) {
            this.quantities += 1
            return quantities
        }
        return quantities
    }

    //decrease order function
    fun decreaseButton(quantities: Int): Int {
        for (i in quantities.until(1)) {

            return quantities
        }
        this.quantities -= 1
        return quantities
    }


    //to insert new coffee order into dataBase
    fun onSave(newCoffeeOrder: CoffeeEntity) {
        viewModelScope.launch {
            insert(newCoffeeOrder)
        }
    }

    //coroutine task[insert] in the backGround
    private suspend fun insert(coffees: CoffeeEntity) {
        withContext(Dispatchers.IO) {
            database.insert(coffees)
        }
    }


}