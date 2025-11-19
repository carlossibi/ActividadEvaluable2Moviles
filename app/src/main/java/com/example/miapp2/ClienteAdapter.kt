package com.example.miapp2

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClienteAdapter(
    private var clientes: List<Cliente>,
    private val onClick: (Cliente) -> Unit,
    private val onLongClick: (Cliente) -> Unit
) : RecyclerView.Adapter<ClienteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre = view.findViewById<TextView>(R.id.tvNombre)
        val email = view.findViewById<TextView>(R.id.tvEmail)
        val telefono = view.findViewById<TextView>(R.id.tvTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.nombre.text = cliente.nombre
        holder.email.text = cliente.email
        holder.telefono.text = cliente.telefono

        holder.itemView.setOnClickListener { onClick(cliente) }

        holder.itemView.setOnLongClickListener {
            // Dialogo de confirmación para eliminar cliente
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("¿Eliminar cliente?")
                .setMessage("¿Seguro que quieres eliminar a ${cliente.nombre}?")
                .setPositiveButton("Sí") { _, _ ->
                    onLongClick(cliente)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }

    override fun getItemCount() = clientes.size

    fun updateData(newList: List<Cliente>) {
        clientes = newList
        notifyDataSetChanged()
    }
}
