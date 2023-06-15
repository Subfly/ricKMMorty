package dev.subfly.rickmmorty.android.view.container.liked.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun WubbaLubbaDubDubDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Be a Rick!")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel
            ) {
                Text("Be a Morty")
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Wubba Lubba Dub Dub!"
            )
        },
        title = {
            Text(text = "Wubba Lubba Dub Dub!")
        },
        text = {
            Text(
                text = "You either die a hero or live long enough to become a villain..."
            )
        },
    )
}