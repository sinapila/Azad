package com.example.Azad

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


@Suppress("UNREACHABLE_CODE")
class AboutUsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var vieww: View = layoutInflater.inflate(R.layout.fragment_about_us, container, false)

        var Button_call_me:Button = vieww.findViewById(R.id.button_call_me)
        var Button_Email_me:Button = vieww.findViewById(R.id.email_me_button)


        Button_call_me.setOnClickListener {
            Log.v("sina","asasdasfd")
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "09922486167"))
            startActivity(intent)
        }

        Button_Email_me.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("sina1385sina1385@gmail.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
                // You can also attach multiple items by passing an ArrayList of Uris
            }
            startActivity(intent)
        }

        return vieww
    }
}