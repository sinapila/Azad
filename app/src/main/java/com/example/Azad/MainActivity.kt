package com.example.Azad

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation_view:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(ConversationFragment())

        val sharedpreferences = getSharedPreferences("Azad", Context.MODE_PRIVATE)


        bottom_navigation_view = findViewById(R.id.bottom_navigation_view)

        bottom_navigation_view.setOnItemSelectedListener {

            when(it.itemId){

                R.id.conversation ->{
                    replaceFragment(ConversationFragment())
                }
                R.id.dictionary ->{
                    replaceFragment(DictionaryFragment())
                }
                R.id.exam ->{
                    replaceFragment(ExamFragment())
                }

            }

            true
        }

    }

    private fun replaceFragment(fragment:Fragment){

        var fragmentManager : FragmentManager = supportFragmentManager

        var fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame_layout,fragment)

        fragmentTransaction.commit()

    }


}