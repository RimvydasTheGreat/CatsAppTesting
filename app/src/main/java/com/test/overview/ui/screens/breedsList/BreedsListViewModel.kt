package com.test.overview.ui.screens.breedsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.test.overview.CatsApplication
import com.test.overview.data.CatsPhotosRepository
import com.test.overview.model.Breed
import kotlinx.coroutines.launch
import java.io.IOError

sealed interface BreedUiState {
    data class Success(val breeds: List<Breed>) : BreedUiState
    object Loading : BreedUiState
    object Error : BreedUiState
}

class BreedsListViewModel(private val repository: CatsPhotosRepository) : ViewModel() {
    var breedUiState: BreedUiState by mutableStateOf(BreedUiState.Loading)
        private set

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            breedUiState = BreedUiState.Loading
            breedUiState = try {
                val result = repository.getBreeds()
                BreedUiState.Success(result)
            } catch (e: IOError) {
                BreedUiState.Error
            } catch (e: Exception) {
                BreedUiState.Error
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as CatsApplication)
                val repository = app.container.catsPhotoRepository
                BreedsListViewModel(repository)
            }
        }
    }
}