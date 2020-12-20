package com.example.coffeeapp.screens.welcome

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentWelcomeBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class Welcome : Fragment() {

    // initialize binding, viewModel and name
    private lateinit var binding: FragmentWelcomeBinding

    private lateinit var viewModel: WelcomeViewModel

    private lateinit var name: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        // attach VM to fragment
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)


        binding.okButton.setOnClickListener {
            info()
            name = binding.editPersonName.text.toString()
        }


        //on click accept button
        binding.acceptButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(WelcomeDirections.actionWelcomeFragmentToOrderFragment(name))
        }
        //on click reject button
        binding.rejectButton.setOnClickListener {
            Snackbar.make(
                requireView(),
                "Please Check Out Our Coffee Collections",
                Snackbar.LENGTH_LONG
            ).show()
        }

        return binding.root
    }

    private fun info() {
        //check if editText is empty
        if (binding.editPersonName.text.isEmpty()) {
            Toast.makeText(context, "Please Tell Us your Name", Toast.LENGTH_SHORT).show()
        } else {
            // necessary buttons disappear or appear
            binding.editPersonName.visibility = View.GONE
            view?.visibility ?: View.GONE

            binding.okButton.visibility = View.GONE

            binding.infoText.text = getString(R.string.format, binding.editPersonName.text)
            binding.infoText.visibility = View.VISIBLE

            binding.rejectButton.visibility = View.VISIBLE
            binding.acceptButton.visibility = View.VISIBLE
        }
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}