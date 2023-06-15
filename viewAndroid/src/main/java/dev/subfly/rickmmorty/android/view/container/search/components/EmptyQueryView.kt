package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.common.enums.ContentFilter

@Composable
fun EmptyQueryView(
    appliedFilter: ContentFilter
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Start Typing To Search",
            tint = LocalContentColor.current.copy(alpha = 0.5F),
            modifier = Modifier
                .size(192.dp)
                .padding(bottom = 32.dp)
        )
        Text(
            text = "Start typing on the bar above to search on ${appliedFilter.toDisplayText()}s",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.fillMaxWidth()
        )
    }
}