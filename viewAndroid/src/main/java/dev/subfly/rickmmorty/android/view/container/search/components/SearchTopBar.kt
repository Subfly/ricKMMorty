package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.common.enums.ContentFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    query: String,
    currentFilter: ContentFilter,
    onQueryChanged: (String) -> Unit,
    onDeleteQuery: () -> Unit,
    onClickChangeFilter: () -> Unit,
    onClickChangeFilterOptions: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                IconButton(
                    onClick = onClickChangeFilter
                ) {
                    Icon(
                        painter = painterResource(
                            id = when (currentFilter) {
                                ContentFilter.CHARACTER -> R.drawable.ic_character
                                ContentFilter.EPISODE -> R.drawable.ic_episode
                                ContentFilter.LOCATION -> R.drawable.ic_location
                            }
                        ),
                        contentDescription = "Change Filter"
                    )
                }
                IconButton(
                    onClick = onClickChangeFilterOptions
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_options),
                        contentDescription = "Change Filter Options"
                    )
                }
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(0.85F),
                value = query,
                onValueChange = onQueryChanged,
                singleLine = true,
                decorationBox = { innerTextField ->
                    val placeholder = buildAnnotatedString {
                        val firstPart = "Start typing to search on "
                        append(firstPart)
                        val emphasized = currentFilter.toDisplayText()
                        append("${emphasized}s")
                        addStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7F)
                            ),
                            start = 0,
                            end = (firstPart + emphasized + 1).length
                        )
                        addStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            ),
                            start = firstPart.length,
                            end = (firstPart + emphasized + 1).length
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(start = 12.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Box {
                            if (query.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )
            IconButton(
                modifier = Modifier
                    .weight(0.15f)
                    .padding(end = 4.dp),
                onClick = onDeleteQuery
            ) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Delete Query"
                )
            }
        }
    }
}