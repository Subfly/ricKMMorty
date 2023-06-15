package dev.subfly.rickmmorty.android.view.container.liked.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoLikedContentView() {

    val subLabelToShow = buildAnnotatedString {
        val firstPart = "Start by looking your favorite characters, episodes or locations on "
        append(firstPart)
        val home = "Home"
        append(home)
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            ),
            start = firstPart.length,
            end = (firstPart + home).length
        )
        val or = " or "
        append(or)
        val search = "Search"
        append(search)
        addStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            ),
            start = (firstPart + home + or).length,
            end = (firstPart + home + or + search).length
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Heart",
            modifier = Modifier
                .size(128.dp)
                .padding(bottom = 32.dp)
        )
        Text(
            text = "No Favorite content found!",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Text(
            text = subLabelToShow,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.fillMaxWidth()
        )
    }
}