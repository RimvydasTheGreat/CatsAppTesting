package com.test.overview.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.overview.ui.screens.breedDetails.BreedDetailsScreen
import com.test.overview.ui.screens.breedsList.BreedListScreen
import com.test.overview.ui.screens.breedsList.BreedUiState
import com.test.overview.ui.screens.login.LoginScreen
import com.test.overview.ui.screens.login.LoginViewModel

enum class Routes(val route: String) {
    Login("Login"),
    BreedsList("Breeds"),
    BreedDetails("Breed Details")
}

@Composable
fun CatsApp(loginViewModel: LoginViewModel, breedsUiState: BreedUiState) {
    val navController = rememberNavController()
    CatsNavHost(
        navController = navController,
        breedsUiState,
        loginViewModel
    )
}

@Composable
fun CatsNavHost(
    navController: NavHostController,
    breedsUiState: BreedUiState,
    loginViewModel: LoginViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                loginViewModel,
                onLoginClick = {
                    val validation = loginViewModel.loginValidation()
                    if (validation) {
                        navController.navigate(Routes.BreedsList.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
        composable(Routes.BreedsList.route) {
            BreedListScreen(
                breedsUiState = breedsUiState,
                onBreedClick = { navController.navigate(Routes.BreedDetails.route) }
            )
            navController.popBackStack(Routes.Login.route, false)
        }

        composable(Routes.BreedDetails.route) {
            BreedDetailsScreen(backClick = { navController.navigateUp() })
        }
    }
}