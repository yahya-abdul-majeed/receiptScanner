package com.yahya.receiptapp.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yahya.receiptapp.R
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface

class ItemListAdapter(private val listOfProducts: MutableList<String>,
                      private val obj: IRecyclerViewInterface
): RecyclerView.Adapter<ItemListAdapter.ItemsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ItemsViewHolder(view,obj )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.textView.text = listOfProducts[position]
    }

    override fun getItemCount(): Int {
        return listOfProducts.size;
    }

    class ItemsViewHolder(view:View, obj: IRecyclerViewInterface):RecyclerView.ViewHolder(view){
        val textView : TextView
        val card: CardView
        init{
            textView = view.findViewById(R.id.textViewHolder)
            card = view.findViewById(R.id.mCardView)
            card.setOnLongClickListener(View.OnLongClickListener {
                obj.onItemLongClick(adapterPosition)
                true;
            })

        }

    }

}

