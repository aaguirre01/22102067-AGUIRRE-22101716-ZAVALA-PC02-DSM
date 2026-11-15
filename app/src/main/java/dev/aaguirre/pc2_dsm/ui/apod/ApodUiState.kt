package dev.aaguirre.pc2_dsm.ui.apod

import dev.aaguirre.pc2_dsm.data.local.ApodEntity

sealed interface ApodUiState {
    object Loading : ApodUiState
    data class Success(val images: List<ApodEntity>) : ApodUiState
    data class Error(val message: String) : ApodUiState
}
