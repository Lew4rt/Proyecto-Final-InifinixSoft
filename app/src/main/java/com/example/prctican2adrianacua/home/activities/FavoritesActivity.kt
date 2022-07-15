package com.example.prctican2adrianacua.home.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.adapters.PromotionsAdapter
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.databinding.ActivityFavoritesBinding
import com.example.prctican2adrianacua.databinding.ActivityHomeBinding

private lateinit var binding: ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {

    var promotionsAdapter : PromotionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar.let {
            it?.title = "Favoritos"
            it?.setHomeAsUpIndicator(R.drawable.back_arrow)
            it?.setDisplayShowHomeEnabled(true)
            it?.setDisplayHomeAsUpEnabled(true)
        }

        binding.linearLayoutFavorites.setBackgroundColor(Color.parseColor("#F8F5F2"))

        var favoriteslist : MutableList<Plate> = AppPreferences.getFavorites(applicationContext)

        promotionsAdapter = PromotionsAdapter(favoriteslist, "fav")
        binding.recyclerViewFavorites.adapter = promotionsAdapter
        binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}