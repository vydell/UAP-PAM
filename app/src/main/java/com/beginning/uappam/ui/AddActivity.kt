package com.beginning.uappam.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.beginning.uappam.api.PlantViewModel
import com.beginning.uappam.api.data.PlantAdd
import com.beginning.uappam.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var contentBinding: ActivityAddBinding
    private val viewModel: PlantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        contentBinding.btnTambah.setOnClickListener {
            val name = contentBinding.etPlantName.text.toString().trim()
            val description = contentBinding.etDesc.text.toString().trim()
            val price = contentBinding.etPrice.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty()) {
                val newPlant = PlantAdd(
                    plant_name = name,
                    description = description,
                    price = price
                )
                viewModel.addPlant(newPlant)
                Toast.makeText(this, "Unit berhasil ditambah", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isi semua kolom", Toast.LENGTH_SHORT).show()
            }

            var intentBack = Intent(this, HomeActivity::class.java)
            startActivity(intentBack)
        }

    }
}