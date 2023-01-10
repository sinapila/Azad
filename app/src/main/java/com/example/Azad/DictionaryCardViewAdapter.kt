package com.example.Azad

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DictionaryCardViewAdapter (private val mList: ArrayList<dictionaryItemView>, val context: Context):RecyclerView.Adapter<DictionaryCardViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dictionary_card_view, parent, false)

        return ViewHolder(view,context)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.world_view.text = ItemsViewModel.world
        holder.translated_world_view.text = ItemsViewModel.world_translated
        holder.lesson_view.text = ItemsViewModel.lesson
        val url = "https://translate.google.com/?sl=en&tl=fa&text=${ItemsViewModel.world}&op=translate"


        holder.onClick={

            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.data = Uri.parse(url)
            context.startActivity(myIntent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View,context:Context) : RecyclerView.ViewHolder(ItemView) {
        val world_view: TextView = itemView.findViewById(R.id.exam_world)
        val translated_world_view: TextView = itemView.findViewById(R.id.exam_about)
        val lesson_view: TextView = itemView.findViewById(R.id.exam_Lesson)
        var onClick:(it:View)->Unit = {}
        init {
            itemView.setOnClickListener {
                onClick(it)
            }
        }


    }

    fun change(){
        notifyDataSetChanged()
    }

    fun setData(newList :ArrayList<dictionaryItemView>) {

        // set new data to list :
        mList.clear()
        mList.addAll( newList )

        notifyDataSetChanged()

    }


}