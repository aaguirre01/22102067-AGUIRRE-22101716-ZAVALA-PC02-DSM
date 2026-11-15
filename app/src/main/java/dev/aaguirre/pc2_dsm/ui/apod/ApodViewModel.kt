package dev.aaguirre.pc2_dsm.ui.apod

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.aaguirre.pc2_dsm.BuildConfig
import dev.aaguirre.pc2_dsm.data.local.ApodEntity
import dev.aaguirre.pc2_dsm.di.AppModule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ApodViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppModule.provideRepository(application)

    val uiState: StateFlow<ApodUiState> = repository.allApod
        .map<List<ApodEntity>, ApodUiState> { images ->
            ApodUiState.Success(images)
        }
        .catch { e ->
            emit(ApodUiState.Error(e.message ?: "Error desconocido"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ApodUiState.Loading
        )

    val favoriteImages: StateFlow<List<ApodEntity>> = repository.favoriteApod
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadApodImages()
    }

    fun loadApodImages() {
        viewModelScope.launch {
            try {
                repository.refreshApods(BuildConfig.NASA_API_KEY)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun refreshData() {
        loadApodImages()
    }

    fun toggleFavorite(id: String, currentFavorite: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(id, !currentFavorite)
        }
    }
}
