package com.example.prctican2adrianacua.home.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.adapters.PlateAdapter
import com.example.prctican2adrianacua.adapters.PromotionsAdapter
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.model.Recipes
import com.example.prctican2adrianacua.data.services.ApiAdapter
import com.example.prctican2adrianacua.data.services.RecipesAppService
import com.example.prctican2adrianacua.databinding.ActivityPlateBinding
import com.example.prctican2adrianacua.databinding.ActivityPromotionsBinding
import com.example.prctican2adrianacua.home.fragments.plateAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

private lateinit var binding: ActivityPromotionsBinding
var plateList : List<Plate>? = null
var promotionsAdapter : PromotionsAdapter? = null

class PromotionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromotionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar.let {
            it?.title = "Promociones del dia"
            it?.setHomeAsUpIndicator(R.drawable.back_arrow)
            it?.setDisplayShowHomeEnabled(true)
            it?.setDisplayHomeAsUpEnabled(true)
        }

        val rvPromotions = binding.recyclerViewPromotions
        binding.linearLayoutPromotions.setBackgroundColor(Color.parseColor("#F8F5F2"))

        val appSrv = ApiAdapter.get().create<RecipesAppService>()
        val call = appSrv.getRandomRecipes("a856d2f4c3414107aac798c414058b42", 10)
        call.enqueue(object: Callback<Recipes>{
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                if(response.isSuccessful) {
                    plateList = response.body()?.plates
                    promotionsAdapter = plateList?.let { PromotionsAdapter(it as MutableList<Plate>, "prom") }

                    rvPromotions.adapter = promotionsAdapter
                    rvPromotions.layoutManager = LinearLayoutManager(applicationContext,
                    LinearLayoutManager.VERTICAL, false)

                } else {
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Toast.makeText(applicationContext, "Error " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}