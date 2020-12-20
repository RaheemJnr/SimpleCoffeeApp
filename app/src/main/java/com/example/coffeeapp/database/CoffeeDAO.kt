package com.example.coffeeapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoffeeDAO {
    @Insert
    suspend fun insert(Coffee: CoffeeEntity)

    @Update
    suspend fun update(Coffee: CoffeeEntity)

    @Query("DELETE FROM `Coffee Table`")
    fun delete()

    @Query("SELECT * FROM `Coffee Table` ORDER BY `coffee_id` DESC ")
    fun getAllCoffeeHistory(): LiveData<List<CoffeeEntity>>

    //yet to use this query cuz i've not design the details page
    @Query("SELECT * FROM `Coffee Table` WHERE `coffee_id` = :key")
    fun getCoffeesWithId(key: Long): LiveData<CoffeeEntity>
}