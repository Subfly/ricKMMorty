package dev.subfly.rickmmorty.android.common.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.subfly.rickmmorty.android.common.components.error.RicKMMortyFloatingErrorComponent
import dev.subfly.rickmmorty.android.common.components.error.RicKMMortyNoContentErrorComponent
import dev.subfly.rickmmorty.android.common.components.loading.RicKMMortyLoadingIndicator

@Composable
fun BaseContentLayout(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    hasNoData: Boolean,
    contentToShow: @Composable () -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            RicKMMortyLoadingIndicator(
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        if (errorMessage.isNotEmpty() && hasNoData.not()) {
            RicKMMortyFloatingErrorComponent(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = errorMessage
            )
        }
        when {
            errorMessage.isNotEmpty() && hasNoData -> RicKMMortyNoContentErrorComponent(
                message = errorMessage,
                onRetryPressed = {}
            )
            isLoading.not() && hasNoData -> Text(text = "This content has no data...")
            else -> contentToShow.invoke()
        }
    }

}