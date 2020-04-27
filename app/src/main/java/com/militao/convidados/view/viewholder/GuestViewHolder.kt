package com.militao.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.militao.convidados.R
import com.militao.convidados.service.model.GuestModel
import com.militao.convidados.view.listener.GuestListener


class GuestViewHolder(itemView : View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {
    fun bind(guest: GuestModel) {

        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remover convidado")
                .setMessage("Deseja remover este convidado ?")
                .setPositiveButton("Remover"){ dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton("Cancelar", null)
                .show()

            true
        }
    }
}