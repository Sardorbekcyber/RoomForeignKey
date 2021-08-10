package uz.eposystems.roomforeignkey

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Client::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("clientId"),
            onDelete = ForeignKey.CASCADE
        ),
    ],
    tableName = "orders"
)
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientId: Int,
    val amount: String
)
