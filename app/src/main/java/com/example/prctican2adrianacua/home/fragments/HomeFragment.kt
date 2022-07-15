package com.example.prctican2adrianacua.home.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.adapters.PlateAdapter
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.model.Recipes
import com.example.prctican2adrianacua.data.services.ApiAdapter
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.data.services.RecipesAppService
import com.example.prctican2adrianacua.databinding.ActivityHomeBinding
import com.example.prctican2adrianacua.databinding.FragmentHomeBinding
import com.example.prctican2adrianacua.home.activities.FavoritesActivity
import com.example.prctican2adrianacua.home.activities.HomeActivity
import com.example.prctican2adrianacua.home.activities.PromotionsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.zip.Inflater

//private lateinit var binding: FragmentHomeBinding
var plateAdapter : PlateAdapter? = null
var plateList : List<Plate>? = null

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)

        //binding = FragmentHomeBinding.inflate(layoutInflater)
        //No logré hacer que el binding funcione dentro de los fragments.
    
    //region FindViews
        val buttonFav = v.findViewById<Button>(R.id.buttonFavoritesHome)
        val buttonOffers = v.findViewById<Button>(R.id.buttonOffersHome)
        val buttonTrends = v.findViewById<Button>(R.id.buttonTrendingsHome)
        val buttonMore = v.findViewById<Button>(R.id.buttonMoreHome)
        val spinner = v.findViewById<Spinner>(R.id.spinnerLocation)
        val rvPlates = v.findViewById<RecyclerView>(R.id.recyclerViewPlates)
        val textViewSeeMorePromotions = v.findViewById<TextView>(R.id.textViewSeeMorePromotions)
        val imageViewNotifications = v.findViewById<ImageView>(R.id.imageViewNotificationBell)
    //endregion
        
    //region Buttons
        buttonFav.setOnClickListener {
            val intent = Intent(activity, FavoritesActivity::class.java)
            startActivity(intent)
        }
        buttonOffers.setOnClickListener {
            Toast.makeText(activity, "Click en ofertas", Toast.LENGTH_SHORT).show()
        }
        buttonTrends.setOnClickListener {
            Toast.makeText(activity, "Click en tendencias", Toast.LENGTH_SHORT).show()
        }
        buttonMore.setOnClickListener {
            Toast.makeText(activity, "Click en ver más", Toast.LENGTH_SHORT).show()
        }
        imageViewNotifications.setOnClickListener {
            Toast.makeText(activity, "Click en notificaciones", Toast.LENGTH_SHORT).show()
        }
    //endregion
    
        val currentUser = context?.let { AppPreferences.getUser(it) }
        if (currentUser != null) {
            val name = currentUser.nombre
            val txtSalute : TextView = v.findViewById(R.id.textViewSaluteUser)
            txtSalute.text = "Hola, $name"
        }
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val adapter = this@HomeFragment.context?.let {
                    ArrayAdapter.createFromResource(
                        it,
                        R.array.locations_sample,
                        R.layout.spinner_item
                    )
                }
                spinner.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        textViewSeeMorePromotions.isClickable = true
        textViewSeeMorePromotions.setOnClickListener {
            val intent = Intent(activity, PromotionsActivity::class.java)
            startActivity(intent)
        }

        val appSrv = ApiAdapter.get().create<RecipesAppService>()
        val call = appSrv.getRandomRecipes("a856d2f4c3414107aac798c414058b42", 5)
        call.enqueue(object: Callback<Recipes> {
            override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                if(response.isSuccessful) {
                    plateList = response.body()?.plates
                    plateAdapter = plateList?.let { PlateAdapter(it as MutableList<Plate>, "prom") }

                    rvPlates.adapter = plateAdapter
                    rvPlates.layoutManager = LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false)

                } else {
                    //Mostrar error
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Recipes>, t: Throwable) {
                Toast.makeText(context, "Error " + t.message, Toast.LENGTH_LONG).show()
                t.printStackTrace()
                //Mostrar error
            }

        })

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}