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

private const val TAG = "====MobilApp"

class MainActivity : AppCompatActivity() {

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        val orderDao = db.orderDao()
        val clientDao = db.clientDao()
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
        val btnSavePerson = findViewById<Button>(R.id.btn_add_person)
        val btnDeletePerson = findViewById<Button>(R.id.btn_delete_person)
        val btnSaveOrder = findViewById<Button>(R.id.btn_add_order)
        val btnDeleteOrder = findViewById<Button>(R.id.btn_delete_order)

        btnSavePerson.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val name = etPersonName.text.toString()
            val surname = etPersonSurname.text.toString()
            appScope.launch {
                clientDao.insertClient(Client(clientId, name, surname))
                val clients: List<Client> = clientDao.getAllClients()
                Log.d(TAG, "clients: $clients")
            }
        }

        btnDeletePerson.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val name = etPersonName.text.toString()
            val surname = etPersonSurname.text.toString()
            appScope.launch {
                clientDao.deleteClient(Client(clientId, name, surname))
                val clients: List<Client> = clientDao.getAllClients()
                Log.d(TAG, "clients: $clients")
            }
        }

        btnSaveOrder.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val amount = etOrderAmount.text.toString()
            appScope.launch {
                orderDao.insertOrder(Order(clientId = clientId, amount = amount))
                val orders: List<Order> = orderDao.getAll()
                Log.d(TAG, "orders: $orders")
            }
        }

        btnDeleteOrder.setOnClickListener {
            val clientId = etOrderId.text.toString().toInt()
            val amount = etOrderAmount.text.toString()
            appScope.launch {
                orderDao.delete(Order(clientId = clientId, amount = amount))
                val orders: List<Order> = orderDao.getAll()
                Log.d(TAG, "orders: $orders")
            }
        }
    }
}