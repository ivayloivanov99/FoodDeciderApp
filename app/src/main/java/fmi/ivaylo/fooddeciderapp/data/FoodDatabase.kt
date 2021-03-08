package fmi.ivaylo.fooddeciderapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities =  [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {

    abstract val foodDAO : FoodDAO

    companion object{
        @Volatile
        private var INSTANCE : FoodDatabase? = null
        fun getInstance(context: Context):FoodDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_data_database"
                    ).build()
                }
                    return instance
            }
        }
    }
}