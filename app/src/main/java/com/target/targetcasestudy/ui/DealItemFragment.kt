package com.target.targetcasestudy.ui

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.target.targetcasestudy.DealListViewModel

import com.target.targetcasestudy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DealItemFragment : Fragment() {

    private val viewModel: DealListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dealId = arguments?.getString(DEAL_ID, "0") ?: "0"
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = "Details"
        }


        viewModel.dealLiveData.observe(viewLifecycleOwner) { it ->
            lifecycleScope.launch {
                view.findViewById<TextView>(R.id.productTitle)?.text = it.title
                view.findViewById<TextView>(R.id.productPrice)?.text =
                    "${it.salePrice.displayString}"
                view.findViewById<TextView>(R.id.regularPrice)?.text =
                    "reg. ${it.salePrice.displayString}"
                view.findViewById<TextView>(R.id.productDetails)?.text = it.description
                Glide.with(requireContext())
                    .load(it.imageUrl)
                    .into(view.findViewById(R.id.productImage))
            }
        }
        viewModel.getDeal(dealId)

    }


    companion object {
        fun newInstance() = DealItemFragment()
        const val DEAL_ID = "dealItemId"
    }
}
