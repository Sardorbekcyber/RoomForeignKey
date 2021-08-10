package uz.eposystems.roomforeignkey.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.eposystems.roomforeignkey.Client

@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    suspend fun getAllClients(): List<Client>

    @Insert
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)
}