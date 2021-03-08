package fmi.ivaylo.fooddeciderapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fmi.ivaylo.fooddeciderapp.data.FoodRepository
import java.lang.IllegalArgumentException

class FoodViewModelFactory(private val repository: FoodRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FoodViewModel::class.java)){
            return FoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}