package com.yahya.receiptapp.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yahya.receiptapp.ItemStoreActivity
import com.yahya.receiptapp.R
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface
import com.yahya.receiptapp.models.Product

class ItemListAdapter(private val listOfProducts: MutableList<Product>,
                      private val listener: IRecyclerViewInterface
): RecyclerView.Adapter<ItemListAdapter.ItemsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ItemsViewHolder(view,listener )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.textView.text = listOfProducts[position].name
    }

    override fun getItemCount(): Int {
        return listOfProducts.size;
    }

    class ItemsViewHolder(view:View, listener: IRecyclerViewInterface):RecyclerView.ViewHolder(view){
        val textView : TextView
        val card: CardView
        init{
            textView = view.findViewById(R.id.textViewHolder)
            card = view.findViewById(R.id.mCardView)
            card.setOnLongClickListener(View.OnLongClickListener {
                listener.onItemLongClick(adapterPosition)
                true;
            })

        }

    }

}

