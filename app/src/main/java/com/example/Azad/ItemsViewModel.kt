package com.example.Azad


data class ItemsViewModel(
    val id:Int,
    val image: Int,
    val text: String,
    val title: String,
    val music: Int,
    val secand_text: ArrayList<TimerText>
)
data class TimerText(
    val text: String,
    val start:Int,
    val end:Int
)
data class dictionaryItemView(
    val world:String,
    val world_translated:String,
    val lesson : String
)