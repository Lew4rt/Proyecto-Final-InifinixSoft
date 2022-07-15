package com.example.prctican2adrianacua.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.plate.PlateActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlateAdapter(var items : MutableList<Plate>, var from : String) : RecyclerView.Adapter<PlateAdapter.ViewHolder>() {

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val INT_KEY = "key"
        var textViewTitle = view.findViewById<TextView>(R.id.textViewPlateTitleItem)
        var textViewSummary = view.findViewById<TextView>(R.id.textViewPlateSummaryItem)
        var textViewPricePerServing = view.findViewById<TextView>(R.id.textViewPlatePriceItem)
        var textViewglutenFree = view.findViewById<TextView>(R.id.textViewAptoCeliacosItem)
        var imageViewImage = view.findViewById<ImageView>(R.id.imageViewPlateItem)
        var buttonDetail = view.findViewById<Button>(R.id.buttonDetailHomeItem)
        var favFab = view.findViewById<FloatingActionButton>(R.id.floatingActionButtonPlateActivity)

        fun onBind(plate: Plate) {
            textViewTitle.text = plate.title
            textViewSummary.text = plate.summary
            textViewPricePerServing.text = "$ " + plate.pricePerServing
            if (!plate.glutenFree) {
                textViewglutenFree.visibility = View.INVISIBLE
            }
            Glide.with(this.itemView.context).load(plate.image).into(imageViewImage)

            buttonDetail.setOnClickListener {
                val intent = Intent(it.context, PlateActivity::class.java)
                intent.putExtra(INT_KEY, plate)
                startActivity(it.context, intent, null)
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
                            , Toast.LENGTH_SHORT).show()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlateAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlateAdapter.ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}