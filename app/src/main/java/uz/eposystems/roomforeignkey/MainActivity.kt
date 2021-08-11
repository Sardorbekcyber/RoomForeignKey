package uz.eposystems.roomforeignkey

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import uz.eposystems.roomforeignkey.model.Client
import uz.eposystems.roomforeignkey.model.Order

private const val TAG = "====MobilApp"

class MainActivity : AppCompatActivity() {

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .fallbackToDestructiveMigration()
            .build()

        val orderDao = db.orderDao()
        val clientDao = db.clientDao()
        val relationalDao = db.relationalDao()

        appScope.launch {
            val orders: List<Order> = orderDao.getAll()
            val clients: List<Client> = clientDao.getAllClients()
            Log.d(TAG, "orders: $orders")
            Log.d(TAG, "clients: $clients")
        }

        val etOrderId = findViewById<EditText>(R.id.et_order_id)
        val etPersonName = findViewById<EditText>(R.id.et_person_name)
        val etPersonSurname = findViewById<EditText>(R.id.et_person_surname)
        val etOrderAmount = findViewById<EditText>(R.id.et_order_amount)
        val etIsActive = findViewById<EditText>(R.id.et_is_loyal)

        val btnSavePerson = findViewById<Button>(R.id.btn_add_person)
        val btnDeletePerson = findViewById<Button>(R.id.btn_delete_person)
        val btnSaveOrder = findViewById<Button>(R.id.btn_add_order)
        val btnDeleteOrder = findViewById<Button>(R.id.btn_delete_order)
        val btnShowRelQuery = findViewById<Button>(R.id.btn_show_relation)

        btnSavePerson.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val name = etPersonName.text.toString()
            val surname = etPersonSurname.text.toString()
            val isActive = etIsActive.text.toString().toBoolean()
            appScope.launch {
                clientDao.insertClient(Client(clientId, name, surname, isActive))
                val clients: List<Client> = clientDao.getAllClients()
                Log.d(TAG, "clients: $clients")
            }
        }

        btnDeletePerson.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val name = etPersonName.text.toString()
            val surname = etPersonSurname.text.toString()
            val isActive = etIsActive.text.toString().toBoolean()
            appScope.launch {
                clientDao.deleteClient(Client(clientId, name, surname, isActive))
                val clients: List<Client> = clientDao.getAllClients()
                Log.d(TAG, "clients: $clients")
            }
        }

        btnSaveOrder.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val amount = etOrderAmount.text.toString()
            appScope.launch {
                orderDao.insertOrder(Order(orderOwnerId = clientId, amount = amount))
                val orders: List<Order> = orderDao.getAll()
                Log.d(TAG, "orders: $orders")
            }
        }

        btnDeleteOrder.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val amount = etOrderAmount.text.toString()
            appScope.launch {
                orderDao.delete(Order(orderOwnerId = clientId, amount = amount))
                val orders: List<Order> = orderDao.getAll()
                Log.d(TAG, "orders: $orders")
            }
        }

        btnShowRelQuery.setOnClickListener {
            appScope.launch {
                val wow = relationalDao.getClientsAndOrders()
                Log.d(TAG, "client And Order: $wow")
            }
        }
    }
}