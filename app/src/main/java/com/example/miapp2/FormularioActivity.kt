package com.example.miapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private var cliente: Cliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        dbHelper = DBHelper(this)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            dbHelper.getAllClientes().find { it.id == id }?.let {
                cliente = it
                etNombre.setText(it.nombre)
                etEmail.setText(it.email)
                etTelefono.setText(it.telefono)
            }
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isBlank() || email.isBlank() || telefono.isBlank()) {
                Toast.makeText(this,"Todos los campos son obligatorios",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this,"Email incorrecto",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (telefono.length < 9) {
                Toast.makeText(this,"Teléfono mínimo 9 dígitos",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cliente == null) {
                dbHelper.insertCliente(Cliente(nombre = nombre, email = email, telefono = telefono))
            } else {
                dbHelper.updateCliente(cliente!!.apply {
                    this.nombre = nombre
                    this.email = email
                    this.telefono = telefono
                })
            }
            finish()
        }
    }
}
