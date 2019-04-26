package com.keiron.techtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.keiron.techtest.section.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentWith(true, MainFragment.newInstance())
    }

    private fun replaceFragmentWith(isRoot: Boolean, fragment: Fragment) {
        if (isRoot) {
            for (i in 0 until supportFragmentManager.backStackEntryCount) {
                supportFragmentManager.popBackStack()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.parent, fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.parent, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
