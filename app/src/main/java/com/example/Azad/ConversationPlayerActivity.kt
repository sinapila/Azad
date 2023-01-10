package com.example.Azad

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class ConversationPlayerActivity : AppCompatActivity() {
    var is_text_showed = false
    var is_played = false
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_player)

        val intent = intent
        val id = intent.getIntExtra("id", 0)
        val data = staticData.conversations_data.find { it.id == id }!!

        val image = data.image
        val text = data.text
        val title = data.title


        var textview = findViewById<TextView>(R.id.conversation_player_des)
        var titleview = findViewById<TextView>(R.id.conversation_player_title)
        var imageview = findViewById<ImageView>(R.id.conversation_player_image)

        var button_play = findViewById<ImageView>(R.id.playButton)

        var seek_bar = findViewById<SeekBar>(R.id.seekBar)
        var bold_view = findViewById<TextView>(R.id.bold_view)




        titleview.text = title
        textview.text = text
        textview.movementMethod = LinkMovementMethod.getInstance();
        textview.highlightColor = Color.TRANSPARENT;
        imageview.setImageResource(image)


        mediaPlayer = MediaPlayer.create(this, data.music)

        seek_bar.max = mediaPlayer.duration / 1000


        data.text.split(" ").forEach { itt:String ->
            textview.makeLinks(
                Pair(itt,
                    View.OnClickListener {

                        val url = "https://translate.google.com/?sl=en&tl=fa&text=$itt&op=translate"
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(url)
                        startActivity(i)

                    })
            )
        }


        button_play.setOnClickListener {
            if (!is_played) {
                button_play.setImageResource(R.drawable.ic_outline_pause_circle_24)
                mediaPlayer.start()
                is_played = !is_played
            } else {
                button_play.setImageResource(R.drawable.ic_outline_play_circle_24)
                mediaPlayer.pause()
                is_played = !is_played
            }
        }

        seek_bar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000)
                }

            }
        })

//
//        val thread = HandlerThread("A Handler Thread")
//        thread.start()
//        val handler = Handler(thread.looper)

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    try {
                        seek_bar.progress = mediaPlayer.currentPosition / 1000
                        var count = 0
                        data.secand_text.forEach {
                            if (mediaPlayer.currentPosition / 1000 <= it.end && mediaPlayer.currentPosition / 1000 >= it.start) {
                                bold_view.text = it.text
                                count += 1
                            }
                        }
                        if (count == 0) {
                            bold_view.text = ""
                        }
                    } catch (e: Exception) {
                        seek_bar.progress = 0
                    }
                }
            }
        }, 100, 100)


    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        var startIndexOfLink = -1
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                override fun updateDrawState(textPaint: TextPaint) {
                    // use this to change the link color
//                    textPaint.color = textPaint.linkColor
                    // toggle below value to enable/disable
                    // the underline shown below the clickable text
//                    textPaint.isUnderlineText = true
                }

                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
//      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
            spannableString.setSpan(
                clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        this.movementMethod =
            LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }


}
