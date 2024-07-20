package com.example.mobile_compose_app

import OverviewScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SsidChart
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SsidChart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.navigation.navigation

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

data class TabItem(
    val title:String,
    val unselectedIcon:ImageVector?=null,
    val selectedIcon: ImageVector?=null
)

@OptIn(ExperimentalMaterial3Api::class)





@Composable
fun createBottomNavBarItems(): List<BottomNavigationItem> {

    val items = listOf(
        BottomNavigationItem(
            title = "Overview",
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Clients",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
            badgeCount = 45
        ),
        BottomNavigationItem(
            title = "Solutions",
            selectedIcon = Icons.Filled.AdsClick,
            unselectedIcon = Icons.Outlined.AdsClick,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "Tools",
            selectedIcon = Icons.Filled.Construction,
            unselectedIcon = Icons.Outlined.Construction,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Markets",
            selectedIcon = Icons.Filled.SsidChart,
            unselectedIcon = Icons.Outlined.SsidChart,
            hasNews = false,
        )
    )

    return items
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


class SampleViewModel: ViewModel() {
}

@Composable
fun LoginNavHost() {
    val loginNavController = rememberNavController()
    NavHost(loginNavController, startDestination = "auth") {

        composable("home") {
            ScaffoldScreen()
        }
        navigation(
            startDestination = "splash_screen",
            route = "auth"
        ) {
            composable("splash_screen") {
                ShowSplashScreen(loginNavController)
            }

            composable("login") {
                val viewModel = it.sharedViewModel<SampleViewModel>(loginNavController)

                LoginScreen(onLoginSuccess = {
                    loginNavController.navigate("home"){
                        popUpTo("auth"){inclusive=true}
                    }

                })
            }

            composable("register") {
                val viewModel = it.sharedViewModel<SampleViewModel>(loginNavController)
            }
            composable("forgot_password") {
                val viewModel = it.sharedViewModel<SampleViewModel>(loginNavController)
            }

        }


    }
}

@Composable
fun OverviewNavHost(paddingValues: PaddingValues){
    val overviewNavController= rememberNavController()
    NavHost(overviewNavController,startDestination="main"){
        composable("main") {
            OverviewScreen(paddingValues)
        }
        composable("screen2") {

        }
    }

}

@Composable
fun ClientNavHost(paddingValues: PaddingValues){
    val clientNavController= rememberNavController()
    NavHost(clientNavController,startDestination="current"){
        composable("current") {
            ClientScreen(paddingValues)
        }
        composable("prospective") {

        }
        composable("manage") {

        }
        composable("compliance") {

        }
    }

}


@Composable
fun SolutionsNavHost(paddingValues: PaddingValues){
    val solutionsNavController= rememberNavController()
    NavHost(solutionsNavController,startDestination="local_invest"){
        composable("local_invest") {
            SolutionScreen(paddingValues)
        }
        composable("offshore_invest") {

        }
        composable("private_equity") {

        }
        composable("life_insure") {

        }
        composable("general_insure") {

        }
        composable("fiduciary") {

        }
    }
}

@Composable
fun ToolsNavHost(paddingValues: PaddingValues){
    val toolsNavController= rememberNavController()
    NavHost(toolsNavController,startDestination="tools_overview"){
        composable("tools_overview") {
            ToolScreen(toolsNavController,paddingValues)
        }
        composable("portfolio_constructor") {
            ConstructorScreen(toolsNavController,paddingValues)
        }
    }
}

@Composable
fun MarketNavHost(paddingValues: PaddingValues){
    val marketsNavController= rememberNavController()
    NavHost(marketsNavController,startDestination="market_overview"){
        composable("market_overview") {

        }
        composable("market_monitor") {

        }
    }
}

@Composable
fun AdminNavHost(){
    val adminNavController= rememberNavController()
    NavHost(adminNavController,startDestination="ui_admin"){
        composable("ui_admin") {

        }
        composable("backend_admin") {

        }
    }
}

