package com.example.labexam04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private var id: Int? = null
    private var topic: String? = null
    private var details: String? = null
    private var date: String? = null
    private var time: String? = null
    private var plevel: String? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_details, container, false)
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        val topicTextView: TextView = rootView.findViewById(R.id.detailFragTxtViewTopicDb)
        val detailsEditText: EditText = rootView.findViewById(R.id.detailFragEditTxtViewDetails)
        val dateEditText: EditText = rootView.findViewById(R.id.detailFragEditTxtViewDate)
        val timeEditText: EditText = rootView.findViewById(R.id.detailFragEditTxtViewTime)
        val plevelTextView: TextView = rootView.findViewById(R.id.detailFragTxtViewPLevel)

        topicTextView.text = topic
        detailsEditText.setText(details)
        dateEditText.setText(date)
        timeEditText.setText(time)
        plevelTextView.text = plevel

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}