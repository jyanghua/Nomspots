package com.example.nomspots.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nomspots.R
import com.example.nomspots.models.FoodItem
import kotlinx.android.synthetic.main.item_food.view.*

class FoodItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<FoodItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun setItems(items: MutableList<FoodItem>) {
        this.items = items
    }

    class ItemViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val itemName: TextView = itemView.item_name
        private val itemRestaurant: TextView = itemView.item_restaurant
        private val itemPrice: TextView = itemView.item_price
        private val itemImage: ImageView = itemView.item_image

        /**
         * Binds the data to the interface elements
         * @param item a food item
         */
        fun bind(item: FoodItem) {
            itemName.text = item.name
            itemRestaurant.text = item.restaurant
            itemPrice.text = String.format("$%.2f", item.price)
            Glide.with(itemView.context)
                .load(item.image)
                .into(itemImage)
        }
    }
}
