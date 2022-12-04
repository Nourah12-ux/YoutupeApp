package com.example.youtupeapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import android.net.NetworkInfo as NetworkInfo1

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
   private lateinit var youTubePlayerView:YouTubePlayerView
     lateinit var player: YouTubePlayer
    val videos: Array<Array<String>> = arrayOf(
        arrayOf("Numbers Game", "CiFyTc1SwPw"),
        arrayOf("Calculator", "ZbZFMDk69IA"),
        arrayOf("Guess the Phrase", "DU1qMhyKv8g"),
        arrayOf("Username and Password", "G_XYXuC8b9M"),
        arrayOf("GUI Username and Password", "sqJWyPhZkDw"),
        arrayOf("Country Capitals", "yBkRLhoVTmc"),
        arrayOf("Database Module", "E-Kb6FgMbVw"))
        var currentVideo = 0
         var timeStamp = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternet()
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(videos[currentVideo][1], timeStamp)
                initializeRV()
            }
        })
    }


    private fun initializeRV(){
        val recyclerView: RecyclerView = findViewById(R.id.rvMain)
        recyclerView.adapter = RVAdapter(videos, player)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
    private fun checkInternet(){
        if(!connectedToInternet()){
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Internet Connection Not Found")
                .setPositiveButton("RETRY"){_, _ -> checkInternet()}
                .show()
        }
    }

    private fun connectedToInternet(): Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo1? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}