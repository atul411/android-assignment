package com.target.targetcasestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.DealListViewModel
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.Deal
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewModel: DealListViewModel by viewModels()
    private val deals = mutableListOf<Deal>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        (activity as AppCompatActivity?)?.title = "List"
        val adapter = DealItemAdapter(deals, requireContext()) { position ->
            val bundle = Bundle()
            bundle.putString(DealItemFragment.DEAL_ID, deals[position].id)
            val dealFragment = DealItemFragment.newInstance()
            dealFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, dealFragment)
                .addToBackStack("tag")
                .commit()
        }
        recyclerView.adapter = adapter
        viewModel.dealList.observe(viewLifecycleOwner) {
            deals.addAll(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.getDealList()
        return view
    }

}
