package com.example.Azad

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExamFragment : Fragment() {



    private var recyclerView: RecyclerView? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<ConversationCardViewAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//         Add the following lines to create RecyclerView

        var vieww: View = layoutInflater.inflate(R.layout.fragment_exam, container, false)


        val data = staticData.exam_data

        recyclerView = vieww.findViewById(R.id.exam_recycler_view);
//            recyclerView?.setHasFixedSize(true);
        var mAdapter = ExamCardViewAdapter(data, vieww.context);
        recyclerView?.layoutManager = LinearLayoutManager(vieww.context);
        recyclerView?.adapter = mAdapter


        return vieww
    }

}

