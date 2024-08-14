package com.codelytical.muvyz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codelytical.muvyz.core.presentation.components.StandardScaffold
import com.codelytical.muvyz.core.presentation.theme.MoviewTheme
import com.codelytical.muvyz.core.presentation.theme.Theme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.AccountScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FavoritesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val themeValue by viewModel.theme.collectAsState(
                initial = Theme.FOLLOW_SYSTEM.themeValue,
                context = Dispatchers.Main.immediate
            )

            MoviewTheme(themeValue) {
                val navController = rememberNavController()
                val navHostEngine = rememberNavHostEngine()

                val newBackStackEntry by navController.currentBackStackEntryAsState()
                val route = newBackStackEntry?.destination?.route

                StandardScaffold(
                    navController = navController,
                    showBottomBar = route in listOf(
                        HomeScreenDestination.route,
                        FavoritesScreenDestination.route,
                        AccountScreenDestination.route,
                        SearchScreenDestination.route,
                    )
                ) { innerPadding ->
                    SharedTransitionLayout {
                        DestinationsNavHost(
                            modifier = Modifier
                                .padding(innerPadding),
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine,
                            dependenciesContainerBuilder = {
                                dependency(this@SharedTransitionLayout)
                            }
                        )
                    }
                }
            }
        }
    }
}
