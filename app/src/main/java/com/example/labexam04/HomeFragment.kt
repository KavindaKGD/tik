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
    private lateinit var toDOList: List<ToDoDataClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = rootView.findViewById(R.id.recycleViewId)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        /*        toDOList = listOf()
        generateExampleData()*/

        // Initialize db asynchronously
        CoroutineScope(Dispatchers.Main).launch {
            db = withContext(Dispatchers.IO) {
                ToDoDatabaseHelper(requireContext())
            }

            // Once db is initialized, populate dataList
            toDOList = db.getAllToDos()
            val adapter = AdapterClass(toDOList, this@HomeFragment)
            recyclerView.adapter = adapter

        }

        return rootView
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


    //private fun generateExampleData() {
    /*// Example data for demonstration purposes
        val exampleData1 = DataClass("Topic 1", "Priority 1", "Time 1")
        val exampleData2 = DataClass("Topic 2", "Priority 2", "Time 2")
        val exampleData3 = DataClass("Topic 3", "Priority 3", "Time 3")

        // Add example data to the list
        dataList.add(exampleData1)
        dataList.add(exampleData2)
        dataList.add(exampleData3)*/
    /*}*/





}