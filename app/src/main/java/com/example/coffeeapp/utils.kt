package com.example.coffeeapp

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.coffeeapp.database.CoffeeEntity
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("MMM-dd-yyyy")
        .format(systemTime).toString()
}

fun formatAmountBought(coffees: CoffeeEntity, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.Quantity, coffees.amountBought.toString()))
    }
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
