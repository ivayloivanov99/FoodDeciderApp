package fmi.ivaylo.fooddeciderapp.data

class FoodRepository(private val dao: FoodDAO) {

    val food = dao.getAllFood()

    suspend fun insert(food: Food){
        dao.insertFood(food)
    }
    suspend fun update(food: Food){
        dao.updateFood(food)
    }
    suspend fun delete(food: Food){
        dao.deleteFood(food)
    }
    suspend fun deleteAll(){
        dao.deleteAll()
    }

}