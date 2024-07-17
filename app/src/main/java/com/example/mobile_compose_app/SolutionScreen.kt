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
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.material.icons.filled.Storm
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.CloseFullscreen
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.RunCircle
import androidx.compose.material.icons.outlined.Storm
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.mobile_compose_app.composables.AssetPerformanceCard
import com.example.mobile_compose_app.composables.instrumentsHeader

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SolutionScreen(paddingValues: PaddingValues) {

    val tabItems = listOf(
        TabIem(
            title = "Local",
            unselectedIcon = Icons.Outlined.ArrowDownward,
            selectedIcon = Icons.Filled.ArrowDownward
        ),
        TabIem(
            title = "Offshore",
            unselectedIcon = Icons.Outlined.ArrowOutward,
            selectedIcon = Icons.Filled.ArrowOutward
        ),
        TabIem(
            title = "Private Equity",
            unselectedIcon = Icons.Outlined.CloseFullscreen,
            selectedIcon = Icons.Filled.CloseFullscreen
        ),
        TabIem(
            title = "Life",
            unselectedIcon = Icons.Outlined.RunCircle,
            selectedIcon = Icons.Filled.RunCircle
        ),
        TabIem(
            title = "GI",
            unselectedIcon = Icons.Outlined.Storm,
            selectedIcon = Icons.Filled.Storm
        ),
        TabIem(
            title = "Fiduciary",
            unselectedIcon = Icons.Outlined.FolderOpen,
            selectedIcon = Icons.Filled.FolderOpen
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
        ) { index ->
            OffshoreSolutions()
        }
    }
}

private val demoAssetList: List<com.example.mobile_compose_app.AssetInfo> = listOf(
    com.example.mobile_compose_app.AssetInfo(
        R.drawable.baseline_dashboard_24,
        "Apple Inc.",
        "AAPL",
        listOf(
            182.789f,
            183.235f,
            184.673f,
            183.091f,
            184.987f,
            185.379f,
            186.492f,
            187.091f,
            185.785f,
            188.284f,
            189.982f,
            190.673f,
            191.579f,
            189.284f,
            192.975f
        ),
        187.00023f,
        1870.3f
    ),
    com.example.mobile_compose_app.AssetInfo(
        R.drawable.baseline_apps_24,
        "Advanced Micro Devices, Inc.",
        "AMD",
        listOf(
            113.518f,
            112.799f,
            111.333f,
            110.235f,
            111.099f,
            112.506f,
            109.985f,
            108.212f,
            109.125f,
            107.531f,
            106.228f,
            105.284f,
            106.031f,
            109.493f
        ),
        113.02211f,
        1356.26f
    ),
    com.example.mobile_compose_app.AssetInfo(
        R.drawable.small_logo,
        "Mongodb Inc.",
        "MDB",
        listOf(
            403.972f,
            401.536f,
            402.241f,
            405.175f,
            402.647f,
            401.829f,
            399.839f,
            398.287f,
            399.671f,
            401.405f,
            397.381f,
            396.093f,
            395.174f,
            392.567f
        ),
        403.00125f,
        3627.011f
    )
)

@Composable
fun OffshoreSolutions(){
    Column {
        instrumentsHeader("Funds")

        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {
            items(demoAssetList.size) { index ->
                AssetPerformanceCard(assetInfo = demoAssetList[index])
            }
        })
    }
}
//fun <T> stateSaver() = Saver<MutableState<T>, Any>(
//    save = { state -> state.value ?: "null" },
//    restore = { value ->
//        @Suppress("UNCHECKED_CAST")
//        mutableStateOf((if (value == "null") null else value) as T)
//    }
//)