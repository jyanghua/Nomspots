package com.example.nomspots

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nomspots.fragments.HomeFragment
import com.example.nomspots.fragments.TrendingFragment
import com.example.nomspots.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializes the bottom navigation bar
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            HomeFragment()
        ).commit()
    }

    // Setting up the listeners for the bottom navigation bar tabs. Assigns corresponding tags.
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        var tag = ""
        when (item.itemId) {
            R.id.nav_home -> {
                selectedFragment = HomeFragment()
                tag = HomeFragment.TAG
            }
            R.id.nav_new_post -> {
                selectedFragment = TrendingFragment()
                tag = TrendingFragment.TAG
            }
            R.id.nav_profile -> {
                selectedFragment = ProfileFragment()
                tag = ProfileFragment.TAG
            }
        }
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            selectedFragment!!, tag
        ).commit()
        true
    }
}