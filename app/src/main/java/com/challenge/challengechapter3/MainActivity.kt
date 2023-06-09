package com.challenge.challengechapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.challengechapter3.Fragment.AbjadFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frame, AbjadFragment())

        fragmentTransaction.commit()
    }
}