package com.example.Azad

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ConversationCardViewAdapter (private val mList: List<ItemsViewModel>, val context: Context):RecyclerView.Adapter<ConversationCardViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.conversation_card_view, parent, false)

        return ViewHolder(view,context)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
        holder.titleView.text = ItemsViewModel.title

        holder.onClick={
            val myIntent = Intent(context, ConversationPlayerActivity::class.java)
            myIntent.putExtra("id", ItemsViewModel.id) //Optional parameters
            context.startActivity(myIntent)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View,context:Context) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.conversation_card_view_image)
        val textView: TextView = itemView.findViewById(R.id.exam_about)
        val titleView: TextView = itemView.findViewById(R.id.exam_world)
        var onClick:(it:View)->Unit = {}
        init {
            itemView.setOnClickListener {
                onClick(it)
            }
        }


    }


}