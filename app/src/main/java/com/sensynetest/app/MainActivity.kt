package com.sensynetest.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinproj.presenter.HospitalListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWithFragment();
    }

    private fun initWithFragment(){
        setContentView(R.layout.activity_fragment_main)
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
                R.id.fragment_container,
                HospitalListFragment()
        );
        transaction.commit();
    }
}
