package com.hilmisalsabilla.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hilmisalsabilla.artspace.ui.theme.ArtSpaceTheme

// Data class for Artwork
data class Artwork(
  val imageResource: Int,
  val title: String,
  val artist: String,
  val year: String
)

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ArtSpaceTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          ArtSpaceScreen()
        }
      }
    }
  }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

  val artworkList = listOf(
    Artwork(R.drawable.monalisa, "Monalisa", "Leonardo da Vinci", "1503"),
    Artwork(R.drawable.starry_night, "Starry Night", "Vincent van Gogh", "1889"),
    Artwork(R.drawable.sunflowers, "Sunflowers", "Vincent van Gogh", "1888")
  )

  var currentIndex by remember { mutableIntStateOf(0) }


  fun showNextArtwork() {
    currentIndex = (currentIndex + 1) % artworkList.size
  }

  fun showPreviousArtwork() {
    currentIndex = if (currentIndex - 1 < 0) artworkList.size - 1 else currentIndex - 1
  }

  val artwork = artworkList[currentIndex]

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Surface(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(0.9f)
        .aspectRatio(3f / 4f),
      shadowElevation = 8.dp,
      color = Color.White
    ) {
      Image(
        painter = painterResource(artwork.imageResource),
        contentDescription = "Artwork Image",
        modifier = Modifier.fillMaxSize()
      )
    }


    Surface(
      modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
      color = Color(0xFFF0F0F0),
      shadowElevation = 4.dp
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = artwork.title,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = artwork.artist,
          fontSize = 16.sp,
          color = Color.Gray
        )
        Text(
          text = "(${artwork.year})",
          fontSize = 16.sp,
          color = Color.Gray
        )
      }
    }


    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Button(
        onClick = { showPreviousArtwork() },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium
      ) {
        Text("Previous")
      }
      Button(
        onClick = { showNextArtwork() },
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium
      ) {
        Text("Next")
      }
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true
)
@Composable
fun ArtSpaceScreenPreview() {
  ArtSpaceTheme {
    ArtSpaceScreen()
  }
}