package dev.subfly.rickmmorty.android.view.container.home.tabs.character

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.CharacterListItem
import dev.subfly.rickmmorty.android.common.extensions.OnReachedBottom
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.state.content.list.characterList.CharacterListEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterContent(
    viewModel: CharacterTabViewModel = koinViewModel(),
    onClickStatus: (String) -> Unit = {}
) {

    val navController = LocalNavigationController.current
    val currentState by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    scrollState.OnReachedBottom {
        viewModel.onEvent(
            event = CharacterListEvent.LoadMore
        )
    }

    BaseContentLayout(
        isLoading = currentState.isLoading,
        errorMessage = currentState.error,
        hasNoData = currentState.data.isEmpty()
    ) {
        LazyColumn(
            state = scrollState
        ) {
            items(currentState.data) { model ->
                CharacterListItem(
                    model = model,
                    onClickStatus = onClickStatus,
                    onClick = {
                        navController.navigate(
                            Detail.Character.passArguments(
                                characterId = model.id,
                                characterName = model.name
                            )
                        )
                    }
                )
            }
        }
    }

}