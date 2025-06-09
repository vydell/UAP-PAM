package com.beginning.uappam.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.beginning.uappam.api.PlantViewModel
import com.beginning.uappam.api.data.Plant
import com.beginning.uappam.api.data.PlantAdd
import com.beginning.uappam.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var contentBinding: ActivityUpdateBinding
    private val viewModel: PlantViewModel by viewModels()
    private var originalPlant: Plant? = null

    companion object {
        const val EXTRA_PLANT = "extra_plant"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        originalPlant = intent.getParcelableExtra(EXTRA_PLANT, Plant::class.java)

        originalPlant?.let {
            contentBinding.etPlantName.setText(it.plantName)
            contentBinding.etDesc.setText(it.description)
            contentBinding.etPrice.setText(it.price)
        }

        contentBinding.btnSimpan.setOnClickListener {
            val originalName = originalPlant?.plantName ?: return@setOnClickListener

            val newName = contentBinding.etPlantName.text.toString().trim()
            val newDescription = contentBinding.etDesc.text.toString().trim()
            val newPrice = contentBinding.etPrice.text.toString().trim()

            if (newName.isNotEmpty() && newDescription.isNotEmpty() && newPrice.isNotEmpty()) {
                val updatedPlantData = PlantAdd(
                    plant_name = newName,
                    description = newDescription,
                    price = newPrice
                )

                viewModel.updatePlant(originalName, updatedPlantData)
                var intentBack = Intent(this, HomeActivity::class.java)
                startActivity(intentBack)

            } else {
                Toast.makeText(this, "Isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }
}