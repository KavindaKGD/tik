package com.example.labexam04

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavBar : AppCompatActivity() {
    //private lateinit var binding: ActivityBottomNavBarBinding
    //@SuppressLint("RestrictedApi")
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//       binding = ActivityBottomNavBarBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        replaceFragment(HomeFragment())
//
//        binding.bottomNavigationView.setOnItemReselectedListener {
//            when(it.itemId){
//
//                R.id.navAdd -> replaceFragment(AddFragment())
//                R.id.navHome -> replaceFragment(HomeFragment())
//                R.id.navUser -> replaceFragment(UserFragment())
//
//                else ->{}
//            }
//        }
        setContentView(R.layout.activity_bottom_nav_bar)
        bottomNavigationView = findViewById(R.id.bottomNavBarID)
        //BottomNavigationItemView = findViewById<>(R.id.bottomNavBarID)

        bottomNavigationView.setOnItemReselectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navAdd -> {
                    replaceFragment(AddFragment())
                    true
                }
                R.id.navHome -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navUser -> {
                    replaceFragment(UserFragment())
                    true
                }

                else -> false


            }


        }

        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frame_layout, fragment)
//        fragmentTransaction.commit()
    }
}