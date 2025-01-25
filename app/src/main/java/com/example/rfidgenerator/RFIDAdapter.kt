package com.example.rfidgenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RFIDAdapter(private var rfidList: MutableList<String>) :
    RecyclerView.Adapter<RFIDAdapter.RFIDViewHolder>() {

    class RFIDViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RFIDViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rfid, parent, false)
        return RFIDViewHolder(view)
    }

    override fun onBindViewHolder(holder: RFIDViewHolder, position: Int) {
        holder.textView.text = rfidList[position]
        holder.deleteButton.setOnClickListener {
            rfidList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, rfidList.size)
        }
    }

    override fun getItemCount() = rfidList.size

    fun updateRFIDs(newRFIDList: MutableList<String>) {
        rfidList.clear()
        rfidList.addAll(newRFIDList)
        notifyDataSetChanged()
    }
}
