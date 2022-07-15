package com.example.prctican2adrianacua.plate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.adapters.IngredientsAdapter
import com.example.prctican2adrianacua.data.model.Ingredient
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.databinding.ActivityPlateBinding
import java.text.DecimalFormat
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

private lateinit var binding: ActivityPlateBinding
val INT_KEY = "key"
var ingredientAdapter : IngredientsAdapter? = null
class PlateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.LinearLayoutIngredients.setBackgroundColor(Color.parseColor("#F8F5F2"))
        val plate: Plate
        plate = intent.extras?.getSerializable(INT_KEY) as Plate
        supportActionBar.let {
            it?.title = plate.title
            it?.setHomeAsUpIndicator(R.drawable.back_arrow)
            it?.setDisplayShowHomeEnabled(true)
            it?.setDisplayHomeAsUpEnabled(true)
        }

        Glide.with(applicationContext).load(plate.image).into(binding.imageViewPlateActivity)
        binding.textViewTitlePlateActivity.text = plate.title
        binding.textViewPricePlateActivity.text = "$ ${plate.pricePerServing}"
        binding.textViewSummaryPlateActivity.text = plate.summary
        if (!plate.glutenFree) {
            binding.textViewAptoCeliacosPlateActivity.visibility = View.INVISIBLE
        }
        val randomStars = Random.nextDouble(2.0, 5.0)
        binding.textViewStarsPlateActivity.text = randomStars.toString().substring(0,3)

        val recyclerViewIngredients = binding.recyclerViewIngredients
        ingredientAdapter = IngredientsAdapter(plate.ingredients)
        recyclerViewIngredients.adapter = ingredientAdapter
        recyclerViewIngredients.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)

        binding.floatingActionButtonPlateActivity.setOnClickListener {
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}


