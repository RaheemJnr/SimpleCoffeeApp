package com.example.coffeeapp.screens.summary

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentSummaryBinding


/**
 * A simple [Fragment] subclass.
 */
class Summary : Fragment() {

    private lateinit var binding: FragmentSummaryBinding

    private val args: SummaryArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_summary,
            container, false
        )


        //received name from order fragment


        //bind message to textView
        binding.summaryView.text = args.summaryMessage

        //bind buyerName to textView
        binding.nameTextView.text = getString(R.string.summary_format, args.name)

        binding.SummaryThanksYou.setOnClickListener {
            findNavController().navigate(SummaryDirections.actionSummaryToWelcomeFragment())
        }

        //add option menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.summary_menu, menu)
        //check if intent can share
        if (null == shareIntent().resolveActivity(requireActivity().packageManager)) {
            //hide menu if it cant share
            menu.findItem(R.id.share).isVisible = false
        }
    }

    //share intent using ShareCompact
    private fun shareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.intent_format, args.name, args.summaryMessage))
            .setType("text/plain")
            .intent
    }

    // start intent
    private fun shareSuccess() {
        startActivity(shareIntent())
    }

    //action to perform when option menu is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
