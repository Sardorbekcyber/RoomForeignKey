package uz.eposystems.roomforeignkey.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.eposystems.roomforeignkey.model.Client

@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    suspend fun getAllClients(): List<Client>

    @Insert
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("SELECT clientId FROM client WHERE isActive == 1 LIMIT 1")
    suspend fun getActive(): Int

}