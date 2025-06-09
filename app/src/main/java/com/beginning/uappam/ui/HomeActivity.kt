package com.beginning.uappam.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.beginning.uappam.adapter.HomeAdapter
import com.beginning.uappam.api.PlantViewModel
import com.beginning.uappam.api.data.Plant
import com.beginning.uappam.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), HomeAdapter.OnItemActionClickListener {
    private lateinit var contentBinding: ActivityHomeBinding
    private val viewModel: PlantViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        setupAdapter()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAllPlants()
    }

    private fun setupAdapter() {
        homeAdapter = HomeAdapter(mutableListOf())
        homeAdapter.setOnItemActionClickListener(this)
    }

    private fun setupRecyclerView() {
        contentBinding.rvPlant.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = homeAdapter
        }
    }

    private fun setupObservers() {
        viewModel.posts.observe(this) { listOfPlants ->
            homeAdapter.updateData(listOfPlants)
        }
        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        contentBinding.btnTambah.setOnClickListener {
            val intentAdd = Intent(this, AddActivity::class.java)
            startActivity(intentAdd)
        }
    }

    override fun onDeleteClick(plant: Plant) {
        plant.plantName?.let { name ->
            viewModel.deletePlant(name)
        }
    }

    override fun onDetailClick(plant: Plant) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PLANT, plant)
        startActivity(intent)
    }
}