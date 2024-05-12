package com.example.labexam04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class AdapterClass(private val datalist:ArrayList<DataClass>):RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rviewTopic:TextView = itemView.findViewById(R.id.ItemLTxtViewTopic)
        val rviewPrioColor:TextView = itemView.findViewById(R.id.ItemLTtextViewPlevel)
        val rviewRemTime:TextView = itemView.findViewById(R.id.ItemLTextViewRemTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = datalist[position]
        holder.rviewTopic.text = currentItem.dataTopic
        holder.rviewPrioColor.text = currentItem.prioColor
        holder.rviewRemTime.text = currentItem.remTime

    }

}