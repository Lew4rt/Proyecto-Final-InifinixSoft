package com.example.prctican2adrianacua.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.model.Ingredient


class IngredientsAdapter(var items: List<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        var textViewIngredient = view.findViewById<TextView>(R.id.textViewIngredientItem)

        fun onBind(ingredient: Ingredient){
            textViewIngredient.text = "- ${ingredient.original}"
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}