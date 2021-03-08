package fmi.ivaylo.fooddeciderapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_data_table")
data class Food (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_name")
    var id: Int,
    @ColumnInfo(name = "food_id")
    var name : String
        )