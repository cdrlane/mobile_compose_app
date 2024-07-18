package com.example.mobile_compose_app

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mobile_compose_app.composables.DropDownItem
import com.example.mobile_compose_app.composables.InstrumentPerformanceCard
import com.example.mobile_compose_app.composables.InstrumentsHeader
import com.example.mobile_compose_app.composables.insDropDownItem
import com.example.mobile_compose_app.ui.theme.clc_navy


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
                .weight(1f),

        ) {page ->
            when(page){
                0 -> {LocalSolutions()}
                1 -> {OffshoreSolutions()}
                2 -> {PrivateEquitySolutions()}
            }


        }
    }
}
private val offshoreTraditionalList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.small_logo,
        "GAAF",
        "Global Equity",
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
        1870.3f,
        riskValue = 6,
        aum=300.3

    ),
    AssetInfo(
        R.drawable.small_logo,
        "GAAF",
        "Global Fixed Income",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "EVERGREEN",
        "Global Equity",
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
        3627.011f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "EVERGREEN",
        "Global Fixed Income",
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
        1356.26f,
        riskValue = 6,
        aum=300.0

    )
)
private val offshoreHedgeFundList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.small_logo,
        "GAAF",
        "Correlated Alpha",
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
        1870.3f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "GAAF",
        "Uncorrelated Alpha",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "EVERGREEN",
        "Absolute",
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
        3627.011f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "EVERGREEN",
        "Partners",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    )
)

private val offshoreHedgeSolutionList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.small_logo,
        "ASPEN",
        "Global",
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
        1870.3f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "ASPEN",
        "Select",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "GAAF",
        "C1",
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
        3627.011f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "ASPEN",
        "C2",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    )
)

private val PrivateEquityList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.small_logo,
        "LEADWOOD",
        "Hillview Partners",
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
        1870.3f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.small_logo,
        "LEADWOOD",
        "Asia Partners",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    ),

    )

private val localFundsList: List<AssetInfo> = listOf(
    AssetInfo(
        R.drawable.corion_logo,
        "CORION",
        "Worldwide Flexible",
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
        1870.3f,
        riskValue = 6,
        aum=300.0
    ),
    AssetInfo(
        R.drawable.corion_logo,
        "CORION",
        "Global Balanced",
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
        1356.26f,
        riskValue = 6,
        aum=300.0
    ),

)

private val insDropDownList:List<DropDownItem> =
    listOf(
    DropDownItem("More information"),
    DropDownItem("Latest report"),
    DropDownItem("Transact")
    )


@Composable
fun OffshoreSolutions(){
    Column() {
        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {

            //workaround to call composable function from within another
            items(count=1){_->InstrumentsHeader("Traditional")}



            items(offshoreTraditionalList.size) { index ->
                val context = LocalContext.current
                InstrumentPerformanceCard(assetInfo = offshoreTraditionalList[index],
                                            dropdownItems=insDropDownList,
                                            modifier=Modifier.fillMaxWidth(),
                                             onItemClick = {
                                                 Toast.makeText(context,
                                                 it.text,
                                                 Toast.LENGTH_LONG).show() }
                                        )
            }

            items(count=1){_->InstrumentsHeader("Hedge Solutions")}


            items(offshoreHedgeSolutionList.size) { index ->
                InstrumentPerformanceCard(assetInfo = offshoreTraditionalList[index],
                    dropdownItems=insDropDownList,
                    modifier=Modifier.fillMaxWidth(),
                    onItemClick = { }
                )

            }

            items(count=1){_->InstrumentsHeader("Hedge Funds")}


            items(offshoreHedgeFundList.size) { index ->
                InstrumentPerformanceCard(assetInfo = offshoreTraditionalList[index],
                    dropdownItems=insDropDownList,
                    modifier=Modifier.fillMaxWidth(),
                    onItemClick = { }
                )

            }
        })
    }
}

@Composable
fun LocalSolutions(){
    Column {
        InstrumentsHeader("Funds")

        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {
            items(localFundsList.size) { index ->
                InstrumentPerformanceCard(assetInfo = localFundsList[index],
                    dropdownItems=insDropDownList,
                    modifier=Modifier.fillMaxWidth(),
                    onItemClick = { }
                )
            }
        })
    }
}

@Composable
fun PrivateEquitySolutions(){
    Column {
        InstrumentsHeader("Funds")

        LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {
            items(PrivateEquityList.size) { index ->
                InstrumentPerformanceCard(assetInfo = PrivateEquityList[index],
                    dropdownItems=insDropDownList,
                    modifier=Modifier.fillMaxWidth(),
                    onItemClick = { }
                )
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