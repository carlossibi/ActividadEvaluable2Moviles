package com.example.miapp2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: ClienteAdapter
    private lateinit var tvContador: TextView
    private lateinit var etBuscar: EditText
    private var clientesAll: List<Cliente> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        clientesAll = dbHelper.getAllClientes()

        adapter = ClienteAdapter(clientesAll, { cliente ->
            val intent = Intent(this, FormularioActivity::class.java)
            intent.putExtra("id", cliente.id)
            startActivity(intent)
        }, { cliente ->
            // Confirmar eliminación
            AlertDialog.Builder(this)
                .setTitle("¿Eliminar cliente?")
                .setMessage("¿Eliminar a ${cliente.nombre}?")
                .setPositiveButton("Sí") { _, _ ->
                    dbHelper.deleteCliente(cliente.id)
                    recargar()
                }
                .setNegativeButton("No", null)
                .show()
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        tvContador = findViewById(R.id.tvContador)
        etBuscar = findViewById(R.id.etBuscar)

        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filtrar()
            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, FormularioActivity::class.java))
        }
    }

    // Carga total al volver
    override fun onResume() {
        super.onResume()
        recargar()
    }

    private fun recargar() {
        clientesAll = dbHelper.getAllClientes()
        filtrar()
    }

    // Filtra clientes y actualiza contador
    private fun filtrar() {
        val query = etBuscar.text.toString().trim().lowercase()
        val filtrados = if (query.isEmpty()) {
            clientesAll
        } else {
            clientesAll.filter {
                it.nombre.lowercase().contains(query) || it.email.lowercase().contains(query)
            }
        }
        adapter.updateData(filtrados)
        tvContador.text = "Total: ${filtrados.size} clientes"
    }
}
