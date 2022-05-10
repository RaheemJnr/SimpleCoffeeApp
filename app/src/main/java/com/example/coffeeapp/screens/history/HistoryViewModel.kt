package com.example.coffeeapp.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.database.CoffeeDAO
import com.example.coffeeapp.database.CoffeeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(private val database: CoffeeDAO, application: Application) :
    AndroidViewModel(application) {


    // [allCoffeeHistory] fetch all the coffee history from the data base
    val allCoffeeHistory: LiveData<List<CoffeeEntity>> = database.getAllCoffeeHistory()

//    val formatHistory = Transformations.map(allCoffeeHistory) {
//        it.toString()
//    }


    fun discard() {
        viewModelScope.launch {
            delete()
        }
    }

    //coroutine task[delete] in the backGround
    private suspend fun delete() {
        withContext(Dispatchers.IO) {
            database.delete()
        }
    }

}