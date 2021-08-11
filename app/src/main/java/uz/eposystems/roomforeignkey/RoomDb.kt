package uz.eposystems.roomforeignkey

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.eposystems.roomforeignkey.dao.ClientDao
import uz.eposystems.roomforeignkey.dao.OrderDao
import uz.eposystems.roomforeignkey.dao.RelationalDao
import uz.eposystems.roomforeignkey.model.Client
import uz.eposystems.roomforeignkey.model.Order

@Database(entities = [Order::class, Client::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun clientDao(): ClientDao
    abstract fun relationalDao(): RelationalDao
}