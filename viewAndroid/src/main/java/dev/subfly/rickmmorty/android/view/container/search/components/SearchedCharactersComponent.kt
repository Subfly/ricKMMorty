package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.subfly.rickmmorty.android.common.components.base.BaseContentLayout
import dev.subfly.rickmmorty.android.common.components.item.CharacterListItem
import dev.subfly.rickmmorty.android.common.extensions.OnReachedBottom
import dev.subfly.rickmmorty.common.models.CharacterModel

@Composable
fun SearchedCharactersComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    charactersList: List<CharacterModel>,
    onLoadMore: () -> Unit,
    onClickItem: (CharacterModel) -> Unit
) {

    val characterListScrollState = rememberLazyListState()

    characterListScrollState.OnReachedBottom {
        onLoadMore()
    }

    BaseContentLayout(
        modifier = modifier
            .fillMaxSize(),
        isLoading = isLoading,
        errorMessage = errorMessage,
        hasNoData = charactersList.isEmpty() && !isLoading
    ) {
        LazyColumn(
            state = characterListScrollState
        ) {
            items(charactersList) { model ->
                CharacterListItem(
                    model = model,
                    onClick = {
                        onClickItem(model)
                    }
                )
            }
        }
    }
}