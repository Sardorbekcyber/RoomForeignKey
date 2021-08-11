package uz.eposystems.roomforeignkey.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(index = true) val orderOwnerId: Int,
    val amount: String
)
