package com.example.labexam04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), AdapterClass.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: ToDoDatabaseHelper
    private lateinit var adapter: AdapterClass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = rootView.findViewById(R.id.recycleViewId)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        adapter = AdapterClass(emptyList(), this)
        recyclerView.adapter = adapter
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData(){
        CoroutineScope(Dispatchers.Main).launch {

            val toDOList = withContext(Dispatchers.IO){
                db = ToDoDatabaseHelper(requireContext())
                db.getAllToDos()
            }
            adapter.refreshData(toDOList)
        }
    }


    override fun onItemClick(todo: ToDoDataClass) {
        val bundle = Bundle().apply {
            putInt("id", todo.toDoId)
            putString("topic", todo.toDoTopic)
            putString("details", todo.toDoDetails)
            putString("date", todo.toDoDate)
            putString("time", todo.toDoTime)
            putString("plevel", todo.toDoPLevel)
        }
        val detailsFragment = DetailsFragment().apply {
            arguments = bundle
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, detailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }



}