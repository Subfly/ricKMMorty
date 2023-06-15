package dev.subfly.rickmmorty.android.common.components.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RicKMMortyNoContentErrorComponent(
    modifier: Modifier = Modifier,
    message: String,
    onRetryPressed: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 48.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        ElevatedButton(
            onClick = onRetryPressed
        ) {
            Text(text = "Retry")
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Icon(
                imageVector = Icons.Outlined.Refresh,
                contentDescription = "Retry"
            )
        }
    }
}

@Composable
fun RicKMMortyFloatingErrorComponent(
    modifier: Modifier = Modifier,
    message: String,
    onRetryPressed: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.errorContainer
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            Text(
                modifier = Modifier.weight(0.8f),
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
            FilledTonalIconButton(
                modifier = Modifier.weight(0.2f),
                onClick = onRetryPressed
            ) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Retry"
                )
            }
        }
    }
}
