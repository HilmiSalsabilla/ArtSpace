import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hilmisalsabilla.artspace.Artwork
import com.hilmisalsabilla.artspace.R

// di ArtSpaceViewModel.kt
class ArtSpaceViewModel : ViewModel() {
  private val _currentIndex = mutableStateOf(0)
  val currentIndex: State<Int> = _currentIndex

  private val artworkList = listOf(
    Artwork(R.drawable.monalisa, "Judul 1", "Seniman 1", "2021"),
    Artwork(R.drawable.starry_night, "Judul 2", "Seniman 2", "2022"),
    Artwork(R.drawable.sunflowers, "Judul 3", "Seniman 3", "2023")
  )

  val currentArtwork: Artwork
    get() = artworkList[_currentIndex.value]

  fun showNextArtwork() {
    _currentIndex.value = (_currentIndex.value + 1) % artworkList.size
  }

  fun showPreviousArtwork() {
    _currentIndex.value = if (_currentIndex.value - 1 < 0) artworkList.size - 1 else _currentIndex.value - 1
  }
}
