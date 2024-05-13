package com.example.labexam04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class AdapterClass(
    private var todo: List<ToDoDataClass>,
    private val itemClickListener: OnItemClickListener
):RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    interface OnItemClickListener {
        fun onItemClick(todo: ToDoDataClass)
    }

    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rviewTopic:TextView = itemView.findViewById(R.id.ItemLTxtViewTopic)
        val rviewPLevel:TextView = itemView.findViewById(R.id.ItemLTtextViewPlevel)
        val rviewRemTime:TextView = itemView.findViewById(R.id.ItemLTextViewRemTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return todo.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = todo[position]
        holder.rviewTopic.text = currentItem.toDoTopic
        holder.rviewPLevel.text = currentItem.toDoPLevel
        holder.rviewRemTime.text = currentItem.toDoDate

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }

    }

    fun refreshData(newToDo: List<ToDoDataClass>){
        todo = newToDo
        notifyDataSetChanged()
    }

}