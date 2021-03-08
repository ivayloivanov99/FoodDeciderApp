package fmi.ivaylo.fooddeciderapp.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FoodDAO {

    @Insert
    suspend fun insertFood(food: Food): Long

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("DELETE FROM food_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM food_data_table")
    fun getAllFood():LiveData<List<Food>>

}