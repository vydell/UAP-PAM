package com.beginning.uappam.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.beginning.uappam.R
import com.beginning.uappam.api.data.Plant
import com.beginning.uappam.databinding.ItemPlantBinding
import com.beginning.uappam.ui.DetailActivity
import java.text.NumberFormat
import java.util.Locale

class HomeAdapter(private var plantList: MutableList<Plant>) :
    RecyclerView.Adapter<HomeAdapter.PlantViewHolder>() {

    inner class PlantViewHolder(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(plant: Plant) {
            binding.tvPlant.text = plant.plantName
            binding.tvPrice.text = formatToRupiah(plant.price.toString())
        }
    }

    interface OnItemActionClickListener {
        fun onDeleteClick(plant: Plant)
        fun onDetailClick(plant: Plant)
    }

    private var listener: OnItemActionClickListener? = null

    fun setOnItemActionClickListener(listener: OnItemActionClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        var currentPlant = plantList[position]
        holder.bind(currentPlant)

        holder.itemView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_detail).setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_PLANT, currentPlant)
            holder.itemView.context.startActivity(intentDetail)
            Toast.makeText(holder.itemView.context, "Detail untuk ${currentPlant.plantName}", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_hapus).setOnClickListener {
            listener?.onDeleteClick(currentPlant)
            Toast.makeText(holder.itemView.context, "Hapus ${currentPlant.plantName}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newPosts: List<Plant>?) {
        plantList.clear()
        if (newPosts != null) {
            plantList.addAll(newPosts)
        }
        notifyDataSetChanged()
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