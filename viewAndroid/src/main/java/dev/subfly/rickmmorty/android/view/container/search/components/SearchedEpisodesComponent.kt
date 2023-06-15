package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.EpisodeListItem
import dev.subfly.rickmmorty.android.common.extensions.OnReachedBottom
import dev.subfly.rickmmorty.common.models.EpisodeModel

@Composable
fun SearchedEpisodesComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    episodesList: List<EpisodeModel>,
    onLoadMore: () -> Unit,
    onClickItem: (EpisodeModel) -> Unit
) {

    val episodeListScrollState = rememberLazyListState()

    episodeListScrollState.OnReachedBottom {
        onLoadMore()
    }

    BaseContentLayout(
        modifier = modifier,
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasNoData = episodesList.isEmpty() && !isLoading
    ) {
        LazyColumn(
            state = episodeListScrollState
        ) {
            items(episodesList) { model ->
                EpisodeListItem(
                    model = model,
                    onClick = {
                        onClickItem(model)
                    }
                )
            }
        }
    }

}