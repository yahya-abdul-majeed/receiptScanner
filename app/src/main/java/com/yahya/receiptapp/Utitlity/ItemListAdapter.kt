package com.yahya.receiptapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter(private val listOfProducts: MutableList<String>): RecyclerView.Adapter<ItemsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.textView.text = listOfProducts[position]
    }

    override fun getItemCount(): Int {
        return listOfProducts.size;
    }


}

class ItemsViewHolder(view:View):RecyclerView.ViewHolder(view){
    val textView : TextView
    init{
        textView = view.findViewById(R.id.textViewHolder)
    }
}