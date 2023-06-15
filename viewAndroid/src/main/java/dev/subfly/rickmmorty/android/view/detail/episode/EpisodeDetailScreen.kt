package dev.subfly.rickmmorty.android.view.detail.episode

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.android.app.LocalLikedContentProvider
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.chip.EpisodeTagContainer
import dev.subfly.rickmmorty.android.common.components.item.CharacterListItem
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.android.view.detail.components.DetailTitle
import dev.subfly.rickmmorty.android.view.detail.components.DetailTopBar
import dev.subfly.rickmmorty.android.view.detail.episode.components.EpisodeDetailMoreInfoContainer
import dev.subfly.rickmmorty.state.content.detail.episode.EpisodeEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailScreen(
    id: Int,
    title: String,
    viewModel: EpisodeDetailViewModel = koinViewModel()
) {

    val navController = LocalNavigationController.current
    val likedContentProvider = LocalLikedContentProvider.current

    val currentState by viewModel.state.collectAsStateWithLifecycle()
    val likedContentState by likedContentProvider.state.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )
    val isEpisodeLiked = id in likedContentState.likedEpisodeIds

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            event = EpisodeEvent.InitWithId(
                id = id
            )
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(
            connection = scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            DetailTopBar(
                title = title,
                isLiked = isEpisodeLiked,
                scrollBehavior = scrollBehavior,
                onBackPressed = {
                    navController.popBackStack()
                },
                onLikePressed = {
                    viewModel.onEvent(
                        event = if (isEpisodeLiked)
                            EpisodeEvent.DislikeEpisode
                        else
                            EpisodeEvent.LikeEpisode
                    )
                }
            )
        }
    ) { paddings ->
        BaseContentLayout(
            modifier = Modifier.padding(paddings),
            isLoading = currentState.isLoading,
            errorMessage = currentState.error,
            hasNoData = currentState.data == null
        ) {
            currentState.data?.let { episodeModel ->
                LazyColumn {
                    item {
                        Card(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            EpisodeDetailMoreInfoContainer(
                                episode = episodeModel.episode,
                                airDate = episodeModel.airDate
                            )
                        }
                    }
                    if (episodeModel.characters.isNotEmpty()) {
                        item {
                            DetailTitle(
                                title = "Characters",
                                icon = R.drawable.ic_character
                            )
                        }
                        items(
                            episodeModel.characters,
                            key = { "${it.name}-${it.id}" }
                        ) { character ->
                            CharacterListItem(
                                model = character,
                                onClick = {
                                    navController.navigate(
                                        Detail.Character.passArguments(
                                            characterId = character.id,
                                            characterName = character.name
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}


