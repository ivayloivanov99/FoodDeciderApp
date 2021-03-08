package fmi.ivaylo.fooddeciderapp.ui

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fmi.ivaylo.fooddeciderapp.data.Food
import fmi.ivaylo.fooddeciderapp.data.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository) : ViewModel(), Observable {

    val food = repository.food
    private var isUpdateOrDelete = false
    private lateinit var foodToUpdateOrDelete: Food

    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
       if(isUpdateOrDelete){
           foodToUpdateOrDelete.name = inputName.value!!
           update(foodToUpdateOrDelete)
       }else {
           val name = inputName.value!!
           insert(Food(0, name))
           inputName.value = null
       }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(foodToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun insert(food: Food) = viewModelScope.launch {
            repository.insert(food)
        }

    fun update(food: Food) = viewModelScope.launch {
        repository.update(food)
        inputName.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
        }

    fun delete(food: Food) = viewModelScope.launch {
        repository.delete(food)
        inputName.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }
    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(food: Food){
        inputName.value = food.name
        isUpdateOrDelete = true
        foodToUpdateOrDelete = food
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}