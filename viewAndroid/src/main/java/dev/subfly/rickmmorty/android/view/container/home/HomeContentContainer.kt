package dev.subfly.rickmmorty.android.view.container.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.app.LocalThemeManager
import dev.subfly.rickmmorty.android.view.container.home.components.HomeTopBar
import dev.subfly.rickmmorty.android.view.container.home.components.ThemeOptionsSheetContent
import dev.subfly.rickmmorty.android.view.container.home.tabs.character.CharacterContent
import dev.subfly.rickmmorty.android.view.container.home.tabs.episode.EpisodeContent
import dev.subfly.rickmmorty.android.view.container.home.tabs.location.LocationContent
import dev.subfly.rickmmorty.common.enums.HomeTab
import dev.subfly.rickmmorty.common.enums.ThemeConfiguration
import dev.subfly.rickmmorty.common.enums.toDisplayText
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.state.theme.ThemeEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeContentContainer() {

    val themeManager = LocalThemeManager.current
    val themeState by themeManager.state.collectAsStateWithLifecycle()

    val tabs = HomeTab.values()

    val scope = rememberCoroutineScope()
    val themeChangeOptionSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val homePagerState = rememberPagerState { tabs.size }
    var currentTab by remember { mutableStateOf(HomeTab.CHARACTERS) }
    val snackbarHostState = remember { SnackbarHostState() }
    var showThemeOptionsModal by remember { mutableStateOf(false) }

    LaunchedEffect(homePagerState) {
        snapshotFlow { homePagerState.currentPage }
            .distinctUntilChanged()
            .collectLatest {
                currentTab = tabs[it]
            }
    }

    Box {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            topBar = {
                HomeTopBar(
                    currentTab = currentTab,
                    tabList = tabs,
                    onClickTab = { tab ->
                        scope.launch {
                            currentTab = tab
                            homePagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    onClickChangeTheme = {
                        showThemeOptionsModal = true
                    }
                )
            }
        ) { paddingValues ->
            HorizontalPager(
                state = homePagerState,
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding()
                )
            ) { page ->
                when (page) {
                    0 -> CharacterContent(
                        onClickStatus = { textToShow ->
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(textToShow)
                            }
                        }
                    )

                    1 -> EpisodeContent(
                        onClickChip = { textToShow ->
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(textToShow)
                            }
                        }
                    )

                    2 -> LocationContent(
                        onClickChip = { textToShow ->
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(textToShow)
                            }
                        }
                    )
                }
            }
        }

        if (showThemeOptionsModal) {
            ModalBottomSheet(
                onDismissRequest = {
                    showThemeOptionsModal = false
                },
                sheetState = themeChangeOptionSheetState
            ) {
                ThemeOptionsSheetContent(
                    selectedThemeConfiguration = themeState.selectedThemeConfiguration,
                    selectedPortalTheme = themeState.selectedPortalTheme,
                    onThemeConfigurationSelected = { newConfiguration ->
                        themeManager.onEvent(
                            event = ThemeEvent.SetThemeConfiguration(
                                newConfiguration = newConfiguration
                            )
                        )
                    },
                    onPortalThemeSelected = { newPortal ->
                        themeManager.onEvent(
                            event = ThemeEvent.SetPortal(
                                newPortalTheme = newPortal
                            )
                        )
                    }
                )
            }
        }

    }

}