package uz.eposystems.roomforeignkey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    @PrimaryKey val clientId: Int,
    val name: String,
    val surname: String,
    val isActive: Boolean
)