package fmi.ivaylo.fooddeciderapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fmi.ivaylo.fooddeciderapp.R
import fmi.ivaylo.fooddeciderapp.data.Food
import fmi.ivaylo.fooddeciderapp.data.FoodDatabase
import fmi.ivaylo.fooddeciderapp.data.FoodRepository
import fmi.ivaylo.fooddeciderapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        val dao = FoodDatabase.getInstance(application).foodDAO
        val repository = FoodRepository(dao)
        val factory = FoodViewModelFactory(repository)
        foodViewModel = ViewModelProvider(this,factory).get(FoodViewModel::class.java)
        binding.myViewModel = foodViewModel
        binding.lifecycleOwner = this
        initRecyclerView()
        displayFoodList()

        val actionBar = supportActionBar
        actionBar!!.title = "Food Database"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun initRecyclerView(){
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)
        displayFoodList()

    }


    private fun displayFoodList(){
        foodViewModel.food.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            binding.foodRecyclerView.adapter = MyRecyclerViewAdapter(it, {selectedItem: Food -> listItemClicked(selectedItem)})
        })
    }

    private fun listItemClicked(food: Food){
        Toast.makeText(this,"The selected food is ${food.name}", Toast.LENGTH_LONG).show()
        foodViewModel.initUpdateAndDelete(food)
    }

}