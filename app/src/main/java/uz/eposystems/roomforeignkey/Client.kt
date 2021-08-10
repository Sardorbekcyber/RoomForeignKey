package uz.eposystems.roomforeignkey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey val id: Int,
    val name: String,
    val surname: String
)