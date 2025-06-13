package com.InformationApplication.Awai

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TutorialFragment : Fragment(R.layout.fragment_tutorial) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the action bar when this fragment is visible
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        // Video resource URLs
        val videoUrls = listOf(
            "android.resource://${requireContext().packageName}/${R.raw.sample1}",
            "android.resource://${requireContext().packageName}/${R.raw.sample2}",
            "android.resource://${requireContext().packageName}/${R.raw.sample3}",
            "android.resource://${requireContext().packageName}/${R.raw.sample4}"
        )

        // Reference the VideoViews from the layout
        val videoViews = listOf(
            view.findViewById<VideoView>(R.id.videoView1),
            view.findViewById<VideoView>(R.id.videoView2),
            view.findViewById<VideoView>(R.id.videoView3),
            view.findViewById<VideoView>(R.id.videoView4)
        )

        // Setup each video with controller and autoplay
        for (i in videoViews.indices) {
            val videoUri = Uri.parse(videoUrls[i])
            val mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videoViews[i])

            videoViews[i].apply {
                setMediaController(mediaController)
                setVideoURI(videoUri)
                requestFocus()
                start() // Autoplay video
            }
        }
    }
}
