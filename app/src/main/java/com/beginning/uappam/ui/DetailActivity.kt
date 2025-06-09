package com.beginning.uappam.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.beginning.uappam.adapter.HomeAdapter
import com.beginning.uappam.api.data.Plant
import com.beginning.uappam.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var contentBinding: ActivityDetailBinding

    companion object {
        const val EXTRA_PLANT = "extra_plant"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        val plant = intent.getParcelableExtra(EXTRA_PLANT, Plant::class.java)

        contentBinding.tvPlantName.text = plant?.plantName
        contentBinding.tvPrice.text = formatToRupiah(plant?.price.toString())
        contentBinding.tvDesc.text = plant?.description

        contentBinding.btnUpdate.setOnClickListener {
            val intentUpdate = Intent(this, UpdateActivity::class.java)
            intentUpdate.putExtra(EXTRA_PLANT, plant)
            startActivity(intentUpdate)
        }
    }

    fun formatToRupiah(price: String): String {
        return try {
            val longPrice = price.toLong()

            val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
            formatter.maximumFractionDigits = 0
            formatter.minimumFractionDigits = 0

            formatter.format(longPrice)

        } catch (e: NumberFormatException) {
            price
        }
    }
}