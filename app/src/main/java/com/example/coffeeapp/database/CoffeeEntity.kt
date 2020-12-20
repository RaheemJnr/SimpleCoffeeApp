package com.example.coffeeapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Coffee Table")
data class CoffeeEntity(
    //poemId primary key to identify each poem
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coffee_id")
    val id: Long = 0L,

    //coffee buyers name
    @ColumnInfo(name = "buyer_name")
    val buyerName: String = "",

    //amount of coffee bought
    @ColumnInfo(name = "amount_bought")
    val amountBought: Int = 0,

    //has whipped cream
    @ColumnInfo(name = "has_whipped")
    val hasWhipped: Boolean = false,

    //has chocolate
    @ColumnInfo(name = "has_chocolate")
    val hasChocolate: Boolean = false,

    // total amount
    @ColumnInfo(name = "total")
    val total: Int = 0,

    //time bought
    @ColumnInfo(name = "time_bought")
    val timeBought: Long = System.currentTimeMillis()

)