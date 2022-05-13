package com.example.coffeeapp.screens.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.coffeeapp.R
import com.example.coffeeapp.database.CoffeeDatabase
import com.example.coffeeapp.database.CoffeeEntity
import com.example.coffeeapp.databinding.FragmentOrderBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class Order : Fragment() {

    //init coffee table for db insertion
    private var coffee: CoffeeEntity = CoffeeEntity()

    /*
     * binding
     * viewModel
     * quantities
     * and args
     */
    private lateinit var binding: FragmentOrderBinding
    private lateinit var viewModel: OrderViewModel

    // args[buyer name] from other fragment
    private val args: OrderArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order,
            container,
            false
        )

        //[application]
        //[dataSource],[viewModelFactory],[viewModel]
        val application = requireNotNull(this.activity).application
        val dataSource = CoffeeDatabase.getInstance(application).CoffeeDAO
        val viewModelFactory = OrderViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[OrderViewModel::class.java]


        //place order text formatting
        binding.placeOrderText.text = getString(R.string.order_format, args.name)

        //onclick minus button
        binding.minusButton.setOnClickListener {
            viewModel.decreaseButton(viewModel.quantities)
            binding.quantityTextView.text = viewModel.quantities.toString()
            if (viewModel.quantities == 0)
                Snackbar.make(
                    requireView(),
                    getString(R.string.minimum_warning),
                    Snackbar.LENGTH_LONG
                ).show()

        }
        //onclick plus button
        binding.plusButton.setOnClickListener {
            viewModel.increaseButton()
            binding.quantityTextView.text = viewModel.quantities.toString()
            if (viewModel.quantities == 10) {
                Snackbar.make(
                    requireView(),
                    getString(R.string.maximun_warning),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        //onclick order button
        binding.orderButton.setOnClickListener {
            placeOrder()
        }


        return binding.root
    }

    // place order
    private fun placeOrder() {
        /* for whipped cream */
        val hasCream = binding.whippedCream.isChecked

        //for chocolate topping
        val hasChocolate = binding.chocolate.isChecked

        val price = calculatePrice(hasCream, hasChocolate)
        val priceMessage = createOrderSummary(hasCream, hasChocolate)

        //insert into db
        coffee =
            CoffeeEntity(coffee.id, args.name, viewModel.quantities, hasCream, hasChocolate, price)
        viewModel.onSave(coffee)

        //move to summary passing along the priceMessage and buyerName
        val action = OrderDirections.actionOrderFragmentToSummary(priceMessage, args.name)
        NavHostFragment.findNavController(this).navigate(action)
    }

    /* function to display order summary
   * then return some text info
   * */
    private fun createOrderSummary(
        hasCream: Boolean, hasChocolate: Boolean
    ): String {
        val price = calculatePrice(hasCream, hasChocolate)
        return getString(R.string.whippedCream, hasCream) +
                "\n" + getString(R.string.hasChocolate, hasChocolate) +
                "\n" + getString(R.string.Quantity_summary, viewModel.quantities) +
                "\n" + getString(
            R.string.total,
            NumberFormat.getCurrencyInstance().format(price)
        )
    }

    /*
    function to calculate the price
    *  */
    private fun calculatePrice(hasCream: Boolean, hasChocolate: Boolean): Int {
        //initial price of coffee
        var initialPrice = 5

        // if user add cream toppings we add extra $1 to the initial price
        if (hasCream) {
            initialPrice += 1
        }

        // if user add chocolate toppings we add extra $2 to the initial price
        if (hasChocolate) {
            initialPrice += 2
        }
        return viewModel.quantities * initialPrice
    }


}