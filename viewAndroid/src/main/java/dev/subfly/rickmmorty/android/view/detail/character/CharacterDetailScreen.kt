package dev.subfly.rickmmorty.android.view.detail.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.android.app.LocalLikedContentProvider
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.EpisodeListItem
import dev.subfly.rickmmorty.android.common.components.item.LocationListItem
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.android.view.detail.character.components.CharacterDetailMoreInfoContainer
import dev.subfly.rickmmorty.android.view.detail.components.DetailTitle
import dev.subfly.rickmmorty.android.view.detail.components.DetailTopBar
import dev.subfly.rickmmorty.state.content.detail.character.CharacterEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    id: Int,
    title: String,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {

    val navController = LocalNavigationController.current
    val likedContentProvider = LocalLikedContentProvider.current

    val currentState by viewModel.state.collectAsStateWithLifecycle()
    val likedContentState by likedContentProvider.state.collectAsStateWithLifecycle()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = rememberTopAppBarState()
    )

    var canImageBeLoaded by remember {
        mutableStateOf(true)
    }
    val isCharacterLiked = id in likedContentState.likedCharacterIds

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            event = CharacterEvent.InitWithId(
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
                isLiked = isCharacterLiked,
                scrollBehavior = scrollBehavior,
                onBackPressed = {
                    navController.popBackStack()
                },
                onLikePressed = {
                    viewModel.onEvent(
                        event = if (isCharacterLiked)
                            CharacterEvent.DislikeCharacter
                        else
                            CharacterEvent.LikeCharacter
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
            currentState.data?.let { characterModel ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {

                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            if (canImageBeLoaded) {
                                SubcomposeAsyncImage(
                                    model = characterModel.imageUrl,
                                    contentDescription = "${characterModel.name} Image",
                                    contentScale = ContentScale.Crop,
                                    filterQuality = FilterQuality.High,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            RoundedCornerShape(12.dp)
                                        ),
                                    onError = {
                                        canImageBeLoaded = false
                                    },
                                    loading = {
                                        CircularProgressIndicator()
                                    }
                                )
                            }
                            CharacterDetailMoreInfoContainer(
                                species = characterModel.species,
                                gender = characterModel.gender,
                                status = characterModel.status,
                                type = characterModel.type
                            )
                        }
                    }

                    characterModel.originLocation?.let { locationModel ->
                        item {
                            DetailTitle(
                                title = "Birth Place",
                                icon = R.drawable.ic_birth
                            )
                        }
                        item {
                            LocationListItem(
                                model = locationModel,
                                onClick = {
                                    navController.navigate(
                                        Detail.Location.passArguments(
                                            locationId = locationModel.id,
                                            locationName = locationModel.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        }
                    }

                    characterModel.lastLocation?.let { locationModel ->
                        item {
                            DetailTitle(
                                title = "Last Seen Location",
                                icon = R.drawable.ic_location
                            )
                        }
                        item {
                            LocationListItem(
                                model = locationModel,
                                onClick = {
                                    navController.navigate(
                                        Detail.Location.passArguments(
                                            locationId = locationModel.id,
                                            locationName = locationModel.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        }
                    }

                    if (characterModel.episodes.isNotEmpty()) {
                        item {
                            DetailTitle(
                                title = "Episodes",
                                icon = R.drawable.ic_episode
                            )
                        }
                        items(
                            characterModel.episodes,
                            key = { "${it.name}-${it.id}" }
                        ) { episode ->
                            EpisodeListItem(
                                model = episode,
                                onClick = {
                                    navController.navigate(
                                        Detail.Episode.passArguments(
                                            episodeId = episode.id,
                                            episodeTitle = episode.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        }
                    }

                }
            }
        }
    }
}

