package com.example.coffeeapp.screens.Developer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentDeveloperBinding


/**
 * A simple [Fragment] subclass.
 */

class Developer : Fragment() {

    private lateinit var binding: FragmentDeveloperBinding

    private val EMAIL = "Mumedian6@gmail.com"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_developer,
            container,
            false
        )

        binding.ContactLink.setOnClickListener {
            //share intent using ShareCompact
            fun shareIntent(): Intent {

                return ShareCompat.IntentBuilder.from(requireActivity())
                    .setType("text/plain")
                    .setEmailTo(arrayOf(EMAIL))
                    .setSubject("Hey MotherFucker What is Wrong With The App")
                    .intent
            }
            startActivity(shareIntent())

        }

        return binding.root
    }

}