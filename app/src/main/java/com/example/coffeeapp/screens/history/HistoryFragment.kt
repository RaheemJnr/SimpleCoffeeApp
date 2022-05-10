package com.example.coffeeapp.screens.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coffeeapp.R
import com.example.coffeeapp.database.CoffeeDatabase
import com.example.coffeeapp.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentHistoryBinding

    //viewModel
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        //[application]
        //[dataSource],[viewModelFactory],[viewModel]
        val application = requireNotNull(this.activity).application
        val dataSource = CoffeeDatabase.getInstance(application).CoffeeDAO
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        //connect xml layout to viewModel
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //use Recycler View Adapter and bind the
        val adapter = HistoryAdapter()
        binding.coffeeHistoryList.adapter = adapter

        //display all poems in the database using recyclerView
        viewModel.allCoffeeHistory.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }


        //
        return binding.root
    }


}