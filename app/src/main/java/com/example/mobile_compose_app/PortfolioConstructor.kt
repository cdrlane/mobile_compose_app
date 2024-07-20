package com.example.mobile_compose_app

import android.text.Layout
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile_compose_app.composables.ListHeader
import kotlin.math.sin

class InstrumentInfo(
    val insCode:String,
    val insName:String
)

val selectedFunds:MutableList<InstrumentInfo> = mutableStateListOf()



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ConstructorScreen(navController: NavController, paddingValues: PaddingValues) {

    val tabItems = listOf(
        TabItem("Build"),
        TabItem("Cumulative Return"),
        TabItem("Rolling Return"),
        TabItem("Risk Return"),
        TabItem("Annual Returns"),
        TabItem("Drawdowns"),
        TabItem("Performance stats"),
        TabItem("Allocations"),
        TabItem("Correlation")
    )

    var selectedTabIndex by rememberSaveable() {mutableStateOf(0)  }
    var pagerState = rememberPagerState {tabItems.size }



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
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        )
        {
            Text(text = "PORTFOLIO CONSTRUCTOR")


        }

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
                0 -> {BuildScreen(paddingValues)}
                1 -> {}
                2 -> {}
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildScreen(paddingValues: PaddingValues) {
    var checkedLocation by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(text = "Offshore", modifier = Modifier.padding(3.dp))
            Switch(
                checked = checkedLocation,
                onCheckedChange = {
                    checkedLocation = it
                }
            )
            Text(text = "Local", modifier = Modifier.padding(3.dp))

        }

        val context = LocalContext.current
        val portfolioModel =
            arrayOf("Low risk value", "Medium risk growth", "Medium risk balanced", "High risk")
        var model_expanded by remember { mutableStateOf(false) }
        var model_selected by remember { mutableStateOf(portfolioModel[0]) }

        ExposedDropdownMenuBox(
            expanded = model_expanded,
            onExpandedChange = {
                model_expanded = !model_expanded
            }
        ) {
            TextField(
                label = { Text("Model portfolio:") },
                value = model_selected,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = model_expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = model_expanded,
                onDismissRequest = { model_expanded = false }
            ) {
                portfolioModel.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            model_selected = item
                            model_expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        val singleFunds = arrayOf("Cantara", "Crake", "Forest Avenue", "HITE Hedge")
        var single_expanded by remember { mutableStateOf(false) }
        var single_selected by remember { mutableStateOf(singleFunds[0]) }

        //workaround to call composable from onlick when instrument updated
        var showDialog by remember { mutableStateOf(false)}

        ExposedDropdownMenuBox(
            expanded = single_expanded,
            onExpandedChange = {
                single_expanded = !single_expanded
            }
        ) {
            TextField(
                label = { Text("Add funds:") },
                value = single_selected,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = single_expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = single_expanded,
                onDismissRequest = { single_expanded = false }
            ) {
                singleFunds.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            single_selected = item
                            single_expanded = false
                            selectedFunds.add(0,InstrumentInfo("testcode", item))

                        }
                    )
                }
            }

            LazyColumn(modifier = Modifier.padding(paddingValues), content = {

                items(selectedFunds.size) { index ->
                    InputChipExample(selectedFunds[index].insName,{
                        selectedFunds.drop(index)
                    })
                }
            }
            )


        }
    }
}


@Composable
fun InputChipExample(
    text: String,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return

    InputChip(
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        label = { Text(text) },
        selected = enabled,
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
    )
}

