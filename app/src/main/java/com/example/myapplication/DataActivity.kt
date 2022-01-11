package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.adapter.ItemRowRepoAdapter
import com.example.myapplication.adapter.ViewPagerAdapter
import com.example.myapplication.fragment.AllRepoFragment
import com.example.myapplication.fragment.StarredRepoFragment
import com.example.myapplication.model.ReposResponseData
import com.example.myapplication.retrofit.ApiClient
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var bar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        supportActionBar?.hide()

        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
        bar = findViewById(R.id.toolbar)


        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(AllRepoFragment(), "All Repo")
        adapter.addFragment(StarredRepoFragment(), "Starred Repo")

        pager.adapter = adapter

        tab.setupWithViewPager(pager)

    }
}