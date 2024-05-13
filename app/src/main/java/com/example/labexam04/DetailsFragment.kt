package com.example.labexam04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.labexam04.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var db:ToDoDatabaseHelper

    private var id: Int? = null
    private var topic: String? = null
    private var details: String? = null
    private var date: String? = null
    private var time: String? = null
    private var plevel: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("id")
            topic = it.getString("topic")
            details = it.getString("details")
            date = it.getString("date")
            time = it.getString("time")
            plevel = it.getString("plevel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        db = ToDoDatabaseHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        binding.detailFragBtnEdit.setOnClickListener {
            val addFragment = AddFragment.newInstance(
                id = id,
                topic = topic,
                details = details,
                date = date,
                time = time,
                plevel = plevel
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, addFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.detailFragBtnDelete.setOnClickListener {
            id?.let { todoId ->
                db.deleteToDoById(todoId)
                Toast.makeText(requireContext(), "ToDo Deleted", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            }
        }
    }
    private fun updateUI() {
        binding.detailFragTxtViewTopicDb.text = topic
        binding.detailFragEditTxtViewDetails.setText(details)
        binding.detailFragEditTxtViewDate.setText(date)
        binding.detailFragEditTxtViewTime.setText(time)
        binding.detailFragTxtViewPLevel.text = plevel
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        val updatedToDo = id?.let { db.getToDoById(it) }
        if (updatedToDo != null) {
            topic = updatedToDo.toDoTopic
            details = updatedToDo.toDoDetails
            date = updatedToDo.toDoDate
            time = updatedToDo.toDoTime
            plevel = updatedToDo.toDoPLevel
            updateUI()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}