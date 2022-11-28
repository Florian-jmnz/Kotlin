import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp1_appkotlin.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository): ViewModel() {
    val series = MutableStateFlow<List<Serie>>(listOf())
    val movies = MutableStateFlow<List<Film>>(listOf())
    val detailMovie = MutableStateFlow(Film())
    val creditsMovie = MutableStateFlow<List<Cast>>(listOf())
    val creditsSerie = MutableStateFlow<List<CastTV>>(listOf())
    val detailSerie = MutableStateFlow(Serie())
    val acteurs = MutableStateFlow<List<Acteur>>(listOf())

    fun getFilmsInitiaux() {
        viewModelScope.launch {
            movies.value = repo.lastMovies()
        }
    }

    fun searchFilms(search: String) {
        viewModelScope.launch {
            movies.value = repo.searchFilms(search)
        }
    }

    fun getDetailFilm(id: String) {
        viewModelScope.launch {
            detailMovie.value = repo.detailFilm(id)
        }
    }

    fun getCreditsFilm(id: String) {
        viewModelScope.launch {
            creditsMovie.value = repo.creditsFilm(id)
        }
    }

    fun getSeriesInitiales() {
        viewModelScope.launch {
            series.value = repo.lastseries()
        }
    }

    fun searchSeries(search: String) {
        viewModelScope.launch {
            series.value = repo.searchseries(search)
        }
    }

    fun getDetailSerie(id: String) {
        viewModelScope.launch {
            detailSerie.value = repo.detailSerie(id)
        }
    }

    fun getCreditsSerie(id: String) {
        viewModelScope.launch {
            creditsSerie.value = repo.creditsSerie(id)
        }
    }

    fun getActeursInitiaux() {
        viewModelScope.launch {
            acteurs.value = repo.lastacteurs()
        }
    }
}