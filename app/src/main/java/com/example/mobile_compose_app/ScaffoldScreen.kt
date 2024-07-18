package com.example.mobile_compose_app

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LongState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {

    val rootNavController= rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()


    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val items = createBottomNavBarItems()


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        color = MaterialTheme.colorScheme.background
    ) {



        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = navBackStackEntry?.destination?.route.toString().replaceFirstChar{ it.uppercase()})
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Go Back"
                            )

                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = "Favourite"
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Admin"
                            )
                        }
                    },
                    scrollBehavior = scrollBehaviour

                )
            },

            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                         val isSelected=item.title.lowercase()==navBackStackEntry?.destination?.route
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                rootNavController.navigate(item.title.lowercase()){
                                    popUpTo(rootNavController.graph.findStartDestination().id){
                                        saveState=true
                                    }
                                    launchSingleTop=true
                                    restoreState=true
                                }
                            },
                            label = {
                                Text(text = item.title)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }

            }
        ){
            padding ->
                NavHost(rootNavController,startDestination="overview"){
                    composable("overview"){
                        OverviewNavHost(padding)
                    }
                    composable("clients"){
                        ClientNavHost(padding)
                    }
                    composable("solutions"){
                        SolutionsNavHost(padding)
                    }
                    composable("tools"){
                        ToolsNavHost(padding)
                    }
                    composable("markets"){
                        MarketNavHost(padding)
                    }
                }
        }




    }
}

