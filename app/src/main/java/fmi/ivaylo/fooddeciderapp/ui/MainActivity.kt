package fmi.ivaylo.fooddeciderapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import fmi.ivaylo.fooddeciderapp.R
import fmi.ivaylo.fooddeciderapp.data.Food
import fmi.ivaylo.fooddeciderapp.data.FoodDAO
import fmi.ivaylo.fooddeciderapp.data.FoodDatabase
import fmi.ivaylo.fooddeciderapp.data.FoodRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar

        actionBar!!.title = "Food Decider"
 

        editFoodButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

        }
        decideButton.setOnClickListener{
            val dao = FoodDatabase.getInstance(application).foodDAO
            val foodList = dao.getAllFood()
            val repository = FoodRepository(dao)
            val factory = FoodViewModelFactory(repository)
            foodViewModel = ViewModelProvider(this,factory).get(FoodViewModel::class.java)

            foodViewModel.food.observe(this, Observer {
                Log.i("MYTAG", it.toString())

                if(it.isEmpty())
                {
                    foodTextView.setText("No food added")
                }

                else
                {
                    val size = it.size
                    val random = Random.nextInt(0, size)
                    foodTextView.setText(it.get(random).name)
                }
            })

        }
    }

}
