package com.example.jikanapianime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jikanapianime.di.ViewModelFactory
import com.example.jikanapianime.ui.screens.detail.DetailScreen
import com.example.jikanapianime.ui.screens.detail.DetailViewModel
import com.example.jikanapianime.ui.screens.home.HomeScreen
import com.example.jikanapianime.ui.screens.home.HomeViewModel


// Navigation destinations used in the app

object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail/{animeId}"

    fun createDetailRoute(animeId: Int) = "detail/$animeId"
}


 //Main navigation graph of the app

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val factory = remember { ViewModelFactory.create() }

    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        composable(Destinations.HOME) {
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onAnimeClick = { animeId ->
                    navController.navigate(Destinations.createDetailRoute(animeId))
                }
            )
        }

        composable(
            route = Destinations.DETAIL,
            arguments = listOf(
                navArgument("animeId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val viewModel: DetailViewModel = viewModel(factory = factory)
            DetailScreen(
                viewModel = viewModel,
                animeId = backStackEntry.arguments?.getInt("animeId") ?: return@composable,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
