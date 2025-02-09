package com.target.targetcasestudy.ui

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.StaticData

class DealItemAdapter(
    val deals: List<Deal>,
    val context: Context,
    val click: (click: Int) -> Unit
) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.itemView.setOnClickListener {
            click(position)
        }
        Glide.with(context)
            .load(item.imageUrl)
            .into(viewHolder.itemView.findViewById(R.id.product_image))
    }
}

class DealItemViewHolder(private val inflatedView: View) : RecyclerView.ViewHolder(inflatedView) {
    private val priceText = inflatedView.findViewById<TextView>(R.id.price_text)
    private val regPrice = inflatedView.findViewById<TextView>(R.id.reg_price)
    private val productName = inflatedView.findViewById<TextView>(R.id.product_name)
    private val aisleLocation = inflatedView.findViewById<TextView>(R.id.aisle_location)
    fun bind(dealItem: Deal) {
        priceText.text = "${dealItem.salePrice.currencySymbol} ${dealItem.salePrice.displayString}"
        regPrice.text = "reg ${dealItem.salePrice.displayString}"
        productName.text = dealItem.title
        aisleLocation.text = "in aisle ${dealItem.aisle}"
    }
}