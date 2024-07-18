package com.example.mobile_compose_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.material.icons.filled.Storm
import androidx.compose.material.icons.filled.VideoCameraBack
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.CloseFullscreen
import androidx.compose.material.icons.outlined.Compress
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.RunCircle
import androidx.compose.material.icons.outlined.Storm
import androidx.compose.material.icons.outlined.VideoCameraBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientScreen(paddingValues: PaddingValues) {

    val tabItems = listOf(
        TabIem(
            title = "Current",
            unselectedIcon = Icons.Outlined.ArrowDownward,
            selectedIcon = Icons.Filled.ArrowDownward
        ),
        TabIem(
            title = "Prospective",
            unselectedIcon = Icons.Outlined.ArrowOutward,
            selectedIcon = Icons.Filled.ArrowOutward
        ),
        TabIem(
            title = "Manage",
            unselectedIcon = Icons.Outlined.ManageAccounts,
            selectedIcon = Icons.Filled.ManageAccounts
        ),
        TabIem(
            title = "Record",
            unselectedIcon = Icons.Outlined.VideoCameraBack,
            selectedIcon = Icons.Filled.VideoCameraBack
        ),
        TabIem(
            title = "Compliance",
            unselectedIcon = Icons.Outlined.Compress,
            selectedIcon = Icons.Filled.Compress
        )
    )

    var selectedTabIndex by rememberSaveable() {
        mutableStateOf(0)
    }

    var pagerState = rememberPagerState {
        tabItems.size
    }

    //animates tabs scrolling
    LaunchedEffect (selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect (pagerState.currentPage,pagerState.isScrollInProgress){
        if(!pagerState.isScrollInProgress){
            selectedTabIndex=pagerState.currentPage
        }
    }
    ///
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    )
    {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                )
            }

        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {page ->
            when(page){
                2-> {CameraContent()}
                3 -> {RecordEvent()}

            }


        }
    }
}