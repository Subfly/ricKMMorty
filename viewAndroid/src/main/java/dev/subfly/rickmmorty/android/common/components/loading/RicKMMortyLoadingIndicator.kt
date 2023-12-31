package dev.subfly.rickmmorty.android.common.components.loading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RicKMMortyLoadingIndicator(
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
    )
}