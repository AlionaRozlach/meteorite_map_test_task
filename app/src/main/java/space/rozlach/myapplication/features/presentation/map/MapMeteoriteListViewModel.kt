package space.rozlach.myapplication.features.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import space.rozlach.myapplication.features.domain.model.Meteorite
import space.rozlach.myapplication.features.domain.repository.MeteoriteRepository
import space.rozlach.myapplication.recourse.Resource
import javax.inject.Inject

@HiltViewModel
class MapMeteoriteListViewModel @Inject constructor(
    private val repository: MeteoriteRepository
) : ViewModel() {

    var meteoritesListVM: List<Meteorite>? = null

    fun getMeteoriteList() {
        viewModelScope.launch {
            repository
                .getMeteorites()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { meteoritesList ->
                                meteoritesListVM = meteoritesList
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {

                        }
                    }
                }
        }
    }
}