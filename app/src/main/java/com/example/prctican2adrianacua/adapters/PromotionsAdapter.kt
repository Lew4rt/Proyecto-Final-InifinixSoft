package com.example.prctican2adrianacua.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.plate.PlateActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PromotionsAdapter(var items : MutableList<Plate>, var from : String) : RecyclerView.Adapter<PromotionsAdapter.ViewHolder>() {

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val INT_KEY = "key"
        var textViewTitle = view.findViewById<TextView>(R.id.textViewTitleItemPromotions)
        var textViewPricePerServing = view.findViewById<TextView>(R.id.textViewPriceItemPromotions)
        var textViewglutenFree =
            view.findViewById<TextView>(R.id.textViewAptoCeliacosItemPromotions)
        var imageViewImage = view.findViewById<ImageView>(R.id.imageViewItemPromotions)
        var textViewDelivery = view.findViewById<TextView>(R.id.textViewDeliveryItemPromotions)
        var cardView = view.findViewById<CardView>(R.id.cardViewItemPromotions)
        var favFab = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonPlateActivity)

        fun onBind(plate: Plate) {
            textViewTitle.text = plate.title
            textViewPricePerServing.text = "$ ${plate.pricePerServing}"
            if (plate.pricePerServing.count() < 5)
                textViewDelivery.visibility = View.INVISIBLE
            if (!plate.glutenFree)
                textViewglutenFree.visibility = View.INVISIBLE
            Glide.with(this.itemView.context).load(plate.image).into(imageViewImage)

            cardView.isClickable = true
            cardView.setOnClickListener {
                val intent = Intent(it.context, PlateActivity::class.java)
                intent.putExtra(INT_KEY, plate)
                ContextCompat.startActivity(it.context, intent, null)
            }
            favFab.setOnClickListener {
                if(from == "fav") {
                    AppPreferences.removeFavorite(it.context, plate)
                    items.remove(plate)
                    Toast.makeText(it.context, "Plato eliminado de favoritos", Toast.LENGTH_SHORT).show()
                    refresh()
                } else {
                    if (AppPreferences.saveFavorite(it.context, plate)){
                        AppPreferences.saveFavorite(it.context, plate)
                        Toast.makeText(it.context
                            , "Plato agregado a favoritos"
                            ,Toast.LENGTH_SHORT).show()
                    }else
                        Toast.makeText(it.context
                            , "El plato ya se encuentra en favoritos"
                            , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun refresh() {
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotions, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromotionsAdapter.ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}