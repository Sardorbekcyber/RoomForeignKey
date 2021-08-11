package uz.eposystems.roomforeignkey.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.eposystems.roomforeignkey.model.Order

@Dao
interface OrderDao {

    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    suspend fun getAll() : List<Order>

    @Delete
    suspend fun delete(order: Order)

}