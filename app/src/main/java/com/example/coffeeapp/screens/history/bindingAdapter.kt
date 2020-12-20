package com.example.coffeeapp.screens.history

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.coffeeapp.convertLongToDateString
import com.example.coffeeapp.database.CoffeeEntity
import com.example.coffeeapp.formatAmountBought

//binding for buyer name
@BindingAdapter("buyerName")
fun TextView.setBuyerName(Item: CoffeeEntity?) {
    Item?.let {
        text = Item.buyerName
    }
}

//binding for amount bought
@BindingAdapter("amountBought")
fun TextView.setAmountBought(Item: CoffeeEntity?) {
    Item?.let {
        text = formatAmountBought(Item, context.resources)
    }
}

//binding for amount bought
@BindingAdapter("timeBought")
fun TextView.setTimeBought(Item: CoffeeEntity?) {
    Item?.let {
        text = convertLongToDateString(Item.timeBought)
    }
}
