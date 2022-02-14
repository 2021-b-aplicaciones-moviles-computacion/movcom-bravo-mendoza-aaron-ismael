package com.example.supermaxi

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView

class InicioFragment : Fragment(R.layout.fragment_inicio) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        val videoView = view.findViewById<VideoView>(R.id.vv_inicio)
        //val mediacontroller = MediaController(context)
        //mediacontroller.setAnchorView(videoView)

        val onLine = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
        val offLine = Uri.parse("android.resource://com.example.supermaxi/${R.raw.video2}")

        //videoView.setMediaController(mediacontroller)
        videoView.setVideoURI(offLine)
        videoView.requestFocus()
        videoView.start()


        return view
    }

}