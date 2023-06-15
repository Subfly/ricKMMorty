package dev.subfly.rickmmorty.android.view.container.home.tabs.episode

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.EpisodeListItem
import dev.subfly.rickmmorty.android.common.extensions.OnReachedBottom
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.state.content.list.episodeList.EpisodeListEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeContent(
    viewModel: EpisodeTabViewModel = koinViewModel(),
    onClickChip: (String) -> Unit = {}
) {

    val navController = LocalNavigationController.current
    val currentState by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    scrollState.OnReachedBottom {
        viewModel.onEvent(
            event = EpisodeListEvent.LoadMore
        )
    }

    BaseContentLayout(
        isLoading = currentState.isLoading,
        errorMessage = currentState.error,
        hasNoData = currentState.data.isEmpty()
    ) {
        LazyColumn {
            items(currentState.data) { model ->
                EpisodeListItem(
                    model = model,
                    onClickChip = onClickChip,
                    onClick = {
                        navController.navigate(
                            Detail.Episode.passArguments(
                                episodeId = model.id,
                                episodeTitle = model.name
                            )
                        )
                    }
                )
            }
        }
    }

}