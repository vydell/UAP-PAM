package com.beginning.uappam.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beginning.uappam.api.data.Plant
import com.beginning.uappam.api.data.PlantAdd
import com.beginning.uappam.api.data.PlantStatus
import kotlinx.coroutines.launch

class PlantViewModel: ViewModel() {
    private val _posts = MutableLiveData<List<Plant>>()
    val posts: LiveData<List<Plant>> = _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchAllPlants() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getAllPlants()
                val listOfPlants = response.data
                _posts.postValue(listOfPlants ?: emptyList())


            } catch (e: Exception) {
                _error.postValue("error: ${e.message}")
            }
        }
    }

    // ini ga dipake sih kak soalnya datanya diparcelize aja dari selected recycler view item
    private val _selectedPlant = MutableLiveData<Plant?>()
    val selectedPlant: MutableLiveData<Plant?> = _selectedPlant

    fun fetchPlant(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getOnePlant(id)
                _selectedPlant.postValue(response.data)
            } catch (e: Exception) {
                _error.postValue("error fetching detail: ${e.message}")
            }
        }
    }

    private val _addPlantStatus = MutableLiveData<PlantStatus>()

    fun addPlant(plant: PlantAdd) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.addPlant(plant)
                _addPlantStatus.postValue(response)
                fetchAllPlants()
            } catch (e: Exception) {
                _error.postValue("Failed to add plant: ${e.message}")
            }
        }
    }

    private val _deleteStatus = MutableLiveData<String>()

    fun deletePlant(plantName: String) {
        viewModelScope.launch {
            try {
                RetrofitClient.instance.deletePlant(plantName)
                _deleteStatus.postValue("Successfully deleted $plantName")
                fetchAllPlants()
            } catch (e: Exception) {
                _error.postValue("Failed to delete plant: ${e.message}")
            }
        }
    }

    private val _updatePlantStatus = MutableLiveData<PlantStatus>()

    fun updatePlant(originalPlantName: String, updatedPlant: PlantAdd) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.updatePlant(originalPlantName, updatedPlant)
                _updatePlantStatus.postValue(response)
                fetchAllPlants()
            } catch (e: Exception) {
                _error.postValue("Failed to update plant: ${e.message}")
            }
        }
    }

}