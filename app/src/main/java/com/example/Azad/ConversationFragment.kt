package com.example.Azad

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Azad.staticData

class ConversationFragment : Fragment() {

//    companion object {
//        fun newInstance() = ConversationFragment()
//    }

    //    private lateinit var viewModel: ConversationViewModel
    private var recyclerView: RecyclerView? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<ConversationCardViewAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var vieww: View = layoutInflater.inflate(R.layout.fragment_conversation, container, false)

        val data = staticData.conversations_data

        recyclerView = vieww.findViewById(R.id.conversation_recycler_view);

        recyclerView?.layoutManager = LinearLayoutManager(vieww.context);
        recyclerView?.adapter = ConversationCardViewAdapter(data, vieww.context);



        return vieww
    }


}