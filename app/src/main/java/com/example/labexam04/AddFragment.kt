package com.example.labexam04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.labexam04.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var db: ToDoDatabaseHelper

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
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listItems = listOf("Low", "Medium", "High")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.addPSpinnerPLevel.adapter = arrayAdapter

        //adding data
        db = ToDoDatabaseHelper(requireContext())

        if(id != null){
            binding.addPEditTextTopic.setText(topic)
            binding.addPEditTextDate.setText(date)
            binding.addPEditTextTime.setText(time)
            binding.addPEditTextMultiLineDetails.setText(details)
            val plevelPosition = listItems.indexOf(plevel)
            binding.addPSpinnerPLevel.setSelection(plevelPosition)
        }

        binding.addPBtnSubmit.setOnClickListener {
            if (validateInput()){
                val newTopic = binding.addPEditTextTopic.text.toString()
                val newDate = binding.addPEditTextDate.text.toString()
                val newTime = binding.addPEditTextTime.text.toString()
                val newDetails = binding.addPEditTextMultiLineDetails.text.toString()
                val newPlevel = binding.addPSpinnerPLevel.selectedItem.toString()

                val todo = ToDoDataClass(id ?: 0, newTopic, newDetails, newPlevel, newDate, newTime)

                if (id != null) {
                    db.updateToDo(todo)
                    Toast.makeText(requireContext(), "ToDo Updated", Toast.LENGTH_SHORT).show()
                } else {
                    db.insertToDo(todo)
                    Toast.makeText(requireContext(), "ToDo Saved", Toast.LENGTH_SHORT).show()
                }

                requireActivity().supportFragmentManager.popBackStack()
            }

        }

    }

    private fun validateInput(): Boolean {
        return when {
            binding.addPEditTextTopic.text.isNullOrEmpty() -> {
                showToast("Please enter a topic")
                false
            }
            binding.addPEditTextDate.text.isNullOrEmpty() -> {
                showToast("Please enter a date")
                false
            }
            binding.addPEditTextTime.text.isNullOrEmpty() -> {
                showToast("Please enter a time")
                false
            }
            binding.addPEditTextMultiLineDetails.text.isNullOrEmpty() -> {
                showToast("Please enter details")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(id: Int?, topic: String?, details: String?, date: String?, time: String?, plevel: String?) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id ?: 0)
                    putString("topic", topic)
                    putString("details", details)
                    putString("date", date)
                    putString("time", time)
                    putString("plevel", plevel)
                }
            }
    }

}