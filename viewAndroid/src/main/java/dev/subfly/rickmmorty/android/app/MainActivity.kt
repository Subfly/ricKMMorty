package dev.subfly.rickmmorty.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.subfly.rickmmorty.android.common.managers.ThemeManager
import dev.subfly.rickmmorty.android.common.theme.RicKMMortyTheme
import dev.subfly.rickmmorty.android.common.providers.LikedContentProvider
import dev.subfly.rickmmorty.android.navigation.RicKMMortyNavHost
import org.koin.androidx.compose.koinViewModel

val LocalNavigationController = compositionLocalOf<NavHostController> {
    error("No nav host found!")
}

val LocalLikedContentProvider = compositionLocalOf<LikedContentProvider> {
    error("No liked content provider found!")
}

val LocalThemeManager = compositionLocalOf<ThemeManager> {
    error("No theme manager found!")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        setContent {

            val navigator = rememberNavController()
            val likedContentProvider: LikedContentProvider = koinViewModel()
            val themeManager: ThemeManager = koinViewModel()

            CompositionLocalProvider(
                LocalNavigationController provides navigator,
                LocalLikedContentProvider provides likedContentProvider,
                LocalThemeManager provides themeManager
            ) {
                RicKMMortyTheme {
                    RicKMMortyNavHost()
                }
            }

        }
    }
}
