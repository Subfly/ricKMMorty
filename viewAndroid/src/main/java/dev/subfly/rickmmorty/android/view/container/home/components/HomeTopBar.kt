package dev.subfly.rickmmorty.android.view.container.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.common.enums.HomeTab
import dev.subfly.rickmmorty.common.extension.getIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    currentTab: HomeTab,
    tabList: Array<HomeTab>,
    onClickTab: (HomeTab) -> Unit,
    onClickChangeTheme: () -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                IconButton(
                    onClick = onClickChangeTheme
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Change Theme"
                    )
                }
            }
        )
        TabRow(
            selectedTabIndex = currentTab.ordinal
        ) {
            tabList.forEach { tab ->
                Tab(
                    selected = tab == currentTab,
                    onClick = {
                        onClickTab(tab)
                    },
                    text = {
                        Text(
                            text = tab.title
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = tab.getIcon(),
                            contentDescription = tab.title
                        )
                    }
                )
            }
        }
    }
}