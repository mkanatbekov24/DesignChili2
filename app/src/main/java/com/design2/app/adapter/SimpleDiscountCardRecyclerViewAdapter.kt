package com.design2.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.design2.app.R
import com.design2.chili2.view.card.IconHolderCardItemView

class SimpleDiscountCardRecyclerViewAdapter(private val context: Context,
//                                            private val onClick: (Any) -> Unit
) :
    RecyclerView.Adapter<SimpleDiscountCardRecyclerViewAdapter.ViewHolder>() {

    private var data: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_discount_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        if (!item.isNullOrEmpty()){
            holder.discountCard.apply {
                setTitle("Test")
                setIcon(item)
                setColor(R.color.purple_200)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val discountCard: IconHolderCardItemView = itemView.findViewById(R.id.card)
    }

    // Method to update the dataset and refresh the adapter
    fun updateListWithIcons(newData: List<String>) {
        data = newData
        notifyDataSetChanged()
    }
}
