package com.example.latihanrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanrecyclerview.ListHandphoneAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var rvHandphone: RecyclerView
    private lateinit var listHandphoneAdapter: ListHandphoneAdapter
    private val list = ArrayList<Handphone>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHandphone = findViewById(R.id.rv_handphone)
        rvHandphone.setHasFixedSize(true)
        list.addAll(getListHandphone())

        // Set up RecyclerView adapter
        listHandphoneAdapter = ListHandphoneAdapter(list)
        rvHandphone.adapter = listHandphoneAdapter
        listHandphoneAdapter.setOnItemClickCallback(object :
            ListHandphoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Handphone) {
                showSelectedHandphone(data)
            }
        })

        // Default RecyclerView layout is linear
        showRecyclerList()
    }
    private fun getListHandphone(): ArrayList<Handphone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription =
            resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHandphone = ArrayList<Handphone>()
        for (i in dataName.indices) {
            val handphone = Handphone(dataName[i], dataDescription[i],
                dataPhoto.getResourceId(i, -1))
            listHandphone.add(handphone)
        }
        return listHandphone
    }

    private fun showSelectedHandphone(handphone: Handphone) {
        Toast.makeText(this, "Kamu memilih " + handphone.name,
            Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_linear -> {
                showRecyclerList()
                true
            }
            R.id.action_grid -> {
                showRecyclerList2()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        rvHandphone.layoutManager = LinearLayoutManager(this)
    }

    private fun showRecyclerList2() {
        rvHandphone.layoutManager = GridLayoutManager(this, 2)
    }
}