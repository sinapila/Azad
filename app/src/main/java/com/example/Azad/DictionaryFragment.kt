package com.example.Azad

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DictionaryFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<ConversationCardViewAdapter.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//         Add the following lines to create RecyclerView
        Log.v("sinaaaa", "onCreateViewHolder: ")

        var vieww: View = layoutInflater.inflate(R.layout.fragment_dictionary, container, false)


        val SearchBar:SearchView = vieww.findViewById(R.id.SearchBarDictianory);

        val data = staticData.dictionary_data
        data.sortWith(compareBy { it.world })
        data.distinctBy { it.world }

        recyclerView = vieww.findViewById(R.id.dicshenery_recycler_view);
//            recyclerView?.setHasFixedSize(true);
        var mAdapter = DictionaryCardViewAdapter(data.clone() as ArrayList<dictionaryItemView>, vieww.context);
        recyclerView?.layoutManager = LinearLayoutManager(vieww.context);
        recyclerView?.adapter = mAdapter


        SearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {


//                val ex_data = ArrayList<dictionaryItemView>()
//
//                val data = staticData.dictionary_data.forEach {
//                    if (newText.isNotEmpty()) {
//                        if (it.world.contains(newText) || it.world_translated.contains(newText)) {
//                            ex_data.add(it)
//                        }
//                    }else{
//                        ex_data.addAll(data)
//                    }
//                }
//                mAdapter.setData(ex_data)


                if (newText!!.isNotEmpty()) {

                    val cloneList = data.clone() as ArrayList<dictionaryItemView>
                    val filteredList = cloneList.filter { foodItem ->
                        foodItem.world.contains( newText ) || foodItem.world_translated.contains(newText) || (newText.toIntOrNull() != null && foodItem.lesson.contains(newText))
                    }

                    mAdapter.setData(  ArrayList( filteredList )  )


                } else {

                    mAdapter.setData(data.clone() as ArrayList<dictionaryItemView>)

                }


                return false
            }
        })


        return vieww
    }
}
