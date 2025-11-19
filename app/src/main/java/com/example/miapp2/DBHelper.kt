package com.example.miapp2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "clientes.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE clientes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "telefono TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS clientes")
        onCreate(db)
    }

    fun insertCliente(cliente: Cliente): Long {
        val values = ContentValues().apply {
            put("nombre", cliente.nombre)
            put("email", cliente.email)
            put("telefono", cliente.telefono)
        }
        return writableDatabase.insert("clientes", null, values)
    }

    fun updateCliente(cliente: Cliente): Int {
        val values = ContentValues().apply {
            put("nombre", cliente.nombre)
            put("email", cliente.email)
            put("telefono", cliente.telefono)
        }
        return writableDatabase.update("clientes", values, "id=?", arrayOf(cliente.id.toString()))
    }

    fun deleteCliente(id: Int): Int {
        return writableDatabase.delete("clientes", "id=?", arrayOf(id.toString()))
    }

    fun getAllClientes(): List<Cliente> {
        val list = mutableListOf<Cliente>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM clientes", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
                list.add(Cliente(id, nombre, email, telefono))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
