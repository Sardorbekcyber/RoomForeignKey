package uz.eposystems.roomforeignkey.model

import androidx.room.Embedded
import androidx.room.Relation

data class ClientAndOrder(
    @Embedded val client: Client,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "orderOwnerId"
    )
    val order: List<Order>
)