package com.example.youtupeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter( private val videosList: Array<Array<String>>,private val player: YouTubePlayer): RecyclerView.Adapter<RVAdapter.VideoViewHolder>(){
    class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentTitle = videosList[position][0]
        val currentLink = videosList[position][1]
        holder.itemView.apply {
        btVideo.text = currentTitle
        btVideo.setOnClickListener {
            player.loadVideo(currentLink, 0f)
        }
        }
    }
    override fun getItemCount(): Int {
       return videosList.size
    }
}