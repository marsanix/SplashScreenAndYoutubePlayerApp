package com.marsanix.splashscreenapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.marsanix.splashscreenapp.ui.theme.SplashScreenAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreenAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val videoUri =
        Uri.parse("android.resource://com.marsanix.splashscreenapp/raw/video")

        Column {

            // contoh cara menggunakan class Youtubeplayer
            YoutubePlayer(
                youtubeVideoId = "E_8LHkn4g-Q",
                lifecycleOwner = LocalLifecycleOwner.current
            )

            Spacer(modifier = Modifier.height(16.dp))

            // VideoPlayer(videoUri = videoUri)

            // PlayVideo()

        }


   
}

@Composable
fun PlayVideo() {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaSource = remember {
        MediaItem.fromUri("android.resource://com.marsanix.splashscreenapp/raw/video/android_introduaction.mp4")
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenAppTheme {
        Greeting("Android")
    }
}