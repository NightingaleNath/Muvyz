package com.codelytical.muvyz.core.domain.model

import com.codelytical.muvyz.R
import com.ramcosta.composedestinations.generated.destinations.AccountScreenDestination
import com.ramcosta.composedestinations.generated.destinations.FavoritesScreenDestination
import com.ramcosta.composedestinations.generated.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SearchScreenDestination


sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String,
) {
    data object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        route = HomeScreenDestination.route
    )
    data object Search: BottomNavItem(
        title = "Search",
        icon = R.drawable.ic_search,
        route = SearchScreenDestination.route,
    )
    data object Favorites: BottomNavItem(
        title = "Favorites",
        icon = R.drawable.ic_star,
        route = FavoritesScreenDestination.route
    )
    data object Account: BottomNavItem(
        title = "Account",
        icon = R.drawable.ic_profile,
        route = AccountScreenDestination.route
    )
}
