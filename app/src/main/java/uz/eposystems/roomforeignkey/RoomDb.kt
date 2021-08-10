package uz.eposystems.roomforeignkey

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.eposystems.roomforeignkey.dao.ClientDao
import uz.eposystems.roomforeignkey.dao.OrderDao

@Database(entities = [Order::class, Client::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun clientDao(): ClientDao
}