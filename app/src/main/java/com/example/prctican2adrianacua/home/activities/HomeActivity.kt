package com.example.prctican2adrianacua.home.activities

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.databinding.ActivityHomeBinding
import com.example.prctican2adrianacua.home.fragments.HomeFragment
import com.example.prctican2adrianacua.home.fragments.ProfileFragment
import com.example.prctican2adrianacua.home.fragments.SearchFragment
import com.example.prctican2adrianacua.home.fragments.ShopFragment
import com.google.android.material.navigation.NavigationBarView


private lateinit var binding: ActivityHomeBinding

val INT_KEY = "key"

private var selectedMenuItem = -1

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña_NoActionBar)
        val view = binding.root
        setContentView(view)
        view.setBackgroundColor(Color.parseColor("#F8F5F2"));

        val bottomMenu : NavigationBarView = binding.bottomMenu
        bottomMenu.setOnItemSelectedListener(this)
        bottomMenu.inflateMenu(R.menu.bottom_menu)
        onNavigationItemSelected(bottomMenu.menu.getItem(0))
    }

    override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
        val fragment : Fragment
        if (selectedMenuItem == item.itemId)
            return true

        selectedMenuItem = item.itemId
        fragment = when (item.itemId) {
            R.id.bottom_menu_home -> HomeFragment.newInstance()
            R.id.bottom_menu_shop -> ShopFragment.newInstance()
            R.id.bottom_menu_search -> SearchFragment.newInstance()
            R.id.bottom_menu_profile -> ProfileFragment.newInstance()
            else -> HomeFragment.newInstance()
        }

        supportFragmentManager.beginTransaction().replace(R.id.HomeLayout, fragment).commit()
        return true
    }
}
//val recyclerViewUsers = findViewById<RecyclerView>()
//val usersAdapter = UsersAdapter(userList)
//recyclerViewUsers.adapter = usersAdapter
//recyclerViewUsers.layoutManager = LinearLayoutManager(
//    this,
//    LinearLayoutManager.VERTICAL,
//    false
//)


        //val extras = intent.extras
        //extras?.let {
        //}
