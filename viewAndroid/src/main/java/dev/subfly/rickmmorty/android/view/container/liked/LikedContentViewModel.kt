package dev.subfly.rickmmorty.android.view.container.liked

import androidx.lifecycle.ViewModel
import dev.subfly.rickmmorty.common.enums.ContentFilter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LikedContentViewModel : ViewModel() {

    private val _filterState = MutableStateFlow(LikedContentUIState())
    val filterState = _filterState.asStateFlow()

    fun onEvent(event: LikedContentUIEvent) {
        when(event) {
            LikedContentUIEvent.CharacterFilterPressed -> {
                updateFilter(filter = ContentFilter.CHARACTER)
            }
            LikedContentUIEvent.EpisodeFilterPressed -> {
                updateFilter(filter = ContentFilter.EPISODE)
            }
            LikedContentUIEvent.LocationFilterPressed -> {
                updateFilter(filter = ContentFilter.LOCATION)
            }
        }
    }

    fun <T>shouldShowContent(
        items: List<T>,
        filters: List<ContentFilter>,
        filter: ContentFilter
    ): Boolean{
        val listNotEmpty = items.isNotEmpty()
        val filtersEmpty = filters.isEmpty()
        val charactersAreFiltered = filter in filters
        return listNotEmpty && (filtersEmpty || charactersAreFiltered)
    }

    private fun updateFilter(filter: ContentFilter) {
        val appliedFilters = _filterState.value.appliedFilters.toMutableList()
        if (filter in appliedFilters) {
            appliedFilters.remove(filter)
        } else {
            appliedFilters.add(filter)
        }
        _filterState.update {
            it.copy(
                appliedFilters = appliedFilters
            )
        }
    }

}