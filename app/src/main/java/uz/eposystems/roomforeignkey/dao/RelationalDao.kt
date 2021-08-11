package uz.eposystems.roomforeignkey.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import uz.eposystems.roomforeignkey.model.ClientAndOrder

@Dao
interface RelationalDao {

    @Transaction
    @Query("SELECT * FROM client")
    fun getClientsAndOrders(): List<ClientAndOrder>

}