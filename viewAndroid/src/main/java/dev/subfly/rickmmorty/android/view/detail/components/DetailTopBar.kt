package dev.subfly.rickmmorty.android.view.detail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    isLiked: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackPressed: () -> Unit = {},
    onLikePressed: () -> Unit = {}
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Like/Remove From Favorites"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onLikePressed
            ) {
                Icon(
                    imageVector = if (isLiked)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = "Like / Remove From Favorites"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}