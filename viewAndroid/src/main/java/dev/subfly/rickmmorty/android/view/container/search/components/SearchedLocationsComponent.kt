package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.LocationListItem
import dev.subfly.rickmmorty.android.common.extensions.OnReachedBottom
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.state.search.SearchEvent

@Composable
fun SearchedLocationsComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    locationsList: List<LocationModel>,
    onLoadMore: () -> Unit,
    onClickItem: (LocationModel) -> Unit
) {

    val locationListScrollState = rememberLazyListState()

    locationListScrollState.OnReachedBottom {
       onLoadMore()
    }


    BaseContentLayout(
        modifier = modifier,
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasNoData = locationsList.isEmpty() && !isLoading
    ) {
        LazyColumn(
            state = locationListScrollState
        ) {
            items(locationsList) { model ->
                LocationListItem(
                    model = model,
                    onClick = {
                        onClickItem(model)
                    }
                )
            }
        }
    }

}