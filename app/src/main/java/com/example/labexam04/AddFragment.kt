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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var spinner: Spinner

    private lateinit var binding: FragmentAddBinding
    private lateinit var db: ToDoDatabaseHelper

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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

        spinner = view.findViewById(R.id.addPSpinnerPLevel)

        val listItems = listOf("Low", "Medium", "High")

        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listItems)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        //adding data
        db = ToDoDatabaseHelper(requireContext())

        binding.addPBtnSubmit.setOnClickListener {
            val topic = binding.addPEditTextTopic.text.toString()
            val date = binding.addPEditTextDate.text.toString()
            val time = binding.addPEditTextTime.text.toString()
            val details = binding.addPEditTextMultiLineDetails.text.toString()
            val priorityLevel = binding.addPSpinnerPLevel.selectedItem.toString()

            val todo = ToDoDataClass(0, topic, details, priorityLevel, date, time)
            db.insertToDo(todo)

            Toast.makeText(requireContext(), "ToDo Saved", Toast.LENGTH_SHORT).show()

            // Optionally, you can clear the input fields after saving
            clearInputFields()
        }

    }

    private fun clearInputFields() {
        binding.addPEditTextTopic.text.clear()
        binding.addPEditTextDate.text.clear()
        binding.addPEditTextTime.text.clear()
        binding.addPEditTextMultiLineDetails.text.clear()
        // You might want to reset the Spinner selection as well
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}