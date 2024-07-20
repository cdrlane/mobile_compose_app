package com.example.mobile_compose_app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_compose_app.composables.ClientCard
import com.example.mobile_compose_app.composables.DropDownItem
import com.example.mobile_compose_app.composables.InstrumentPerformanceCard



private val clientSample: List<ClientInfo> = listOf(
    ClientInfo(
        R.drawable.person,
        "Short",
        "Volatility",
        "Mrs",
        listOf(ClientId("RSA ID","9852320214562")),
        6,
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
        25.0f,
        362.5f
    ),ClientInfo(
        R.drawable.person,
        "Efficient",
        "Frontier",
        "Prof",
        listOf(ClientId("RSA ID","45265420214562")),
        4,
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
        29.0f,
        12.5f
    ),ClientInfo(
        R.drawable.person,
        "Long",
        "Equity",
        "Dr",
        listOf(ClientId("RSA ID","6523520214562")),
        9,
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
        85.0f,
        62.5f
    ),


)

private val clientDropDownList:List<DropDownItem> =
    listOf(
        DropDownItem("Personal information"),
        DropDownItem("Latest report"),
        DropDownItem("Transact")
    )

@Composable
fun ClientList(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {
           LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp), content = {
            items(clientSample.size) { index ->
                ClientCard(clientInfo = clientSample[index],
                    dropdownItems=clientDropDownList,
                    modifier=Modifier.fillMaxWidth(),
                    onItemClick = { }
                )
            }
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClientScreen(paddingValues: PaddingValues) {

    val tabItems = listOf(
        TabItem(
            title = "Current",
            unselectedIcon = Icons.Outlined.ArrowDownward,
            selectedIcon = Icons.Filled.ArrowDownward
        ),
        TabItem(
            title = "Prospective",
            unselectedIcon = Icons.Outlined.ArrowOutward,
            selectedIcon = Icons.Filled.ArrowOutward
        ),
        TabItem(
            title = "Manage",
            unselectedIcon = Icons.Outlined.ManageAccounts,
            selectedIcon = Icons.Filled.ManageAccounts
        ),
        TabItem(
            title = "Record",
            unselectedIcon = Icons.Outlined.VideoCameraBack,
            selectedIcon = Icons.Filled.VideoCameraBack
        ),
        TabItem(
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
                                item.selectedIcon!!
                            } else item.unselectedIcon!!,
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
                0->{ClientList()}
                2-> {CameraContent()}
                3 -> {RecordEvent()}

            }


        }
    }
}

