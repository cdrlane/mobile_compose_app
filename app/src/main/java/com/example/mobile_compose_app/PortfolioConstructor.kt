package com.example.mobile_compose_app



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarPlotData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.example.mobile_compose_app.ui.theme.clc_navy
import com.example.mobile_compose_app.ui.theme.clc_stone
import kotlin.math.roundToInt

class InstrumentInfo(
    val insCode:String,
    val insName:String,
    var weight:Float,
    val coreHolding:Boolean,
    var unlocked:Boolean

 )


//val selectedFunds: SnapshotStateMap<String, InstrumentInfo> = mutableStateMapOf()



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
                1 -> { CumPerformanceScreen(paddingValues) }
                2 -> {}
                3 -> {}
                4 -> {AnnualReturns(paddingValues)}
            }
        }
    }
}

@Composable
fun AnnualReturns(paddingValues: PaddingValues) {
    val maxRange = 50
    val yStepSize = 10


    val barData:List<BarData> = listOf(
        BarData(point=Point(0f,23f),
            color= clc_navy,
            label="One"),
    BarData(point=Point(1f,27f),
        color= clc_stone,
        label="Two")
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .labelData { index -> barData[index].label }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 20.dp,
            barWidth = 25.dp
        ),
        showYAxis = true,
        showXAxis = true,
        horizontalExtraSpace = 10.dp,
    )
    BarChart(modifier = Modifier.fillMaxWidth(), barChartData = barChartData)

}

@Composable
fun CumPerformanceScreen(paddingValues: PaddingValues){
    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        //.backgroundColor(Color.Blue)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(1)
        //.backgroundColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / 1
            (i * yScale).toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )
    
    co.yml.charts.ui.linechart.LineChart(
        modifier = Modifier
            .fillMaxWidth(),
        lineChartData = lineChartData
    )
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildScreen(paddingValues: PaddingValues) {
    var checkedLocation by remember { mutableStateOf(false) }

    val selectedFunds=remember {mutableStateMapOf<String,InstrumentInfo>()}
    var portfolioEmpty by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {Row(
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

            if(selectedFunds.size!=0){portfolioEmpty=false}else{portfolioEmpty=true}

            Button(onClick={},content={Text(text="Run")}, enabled=!portfolioEmpty)
        }

        val context = LocalContext.current
        val portfolioModel =
            arrayOf("Low risk value", "GAAF C1","Medium risk growth", "Medium risk balanced","GAAF C2", "High risk")
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
                            selectedFunds[item]=
                                InstrumentInfo(
                                    item,
                                    item,
                                    100f,
                                    true,
                                    true
                                )
                                }
                            )
                        }
                    }
                }

        val singleFunds = arrayOf("Cantara", "Crake", "Forest Avenue", "HITE Hedge")
        var single_expanded by remember { mutableStateOf(false) }
        var single_selected by remember { mutableStateOf(singleFunds[0]) }

        var selectedWeight by remember {mutableStateOf(5f)}
        var sliderFinished by remember {mutableStateOf(false)}

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
                            selectedFunds[item]=
                                InstrumentInfo(
                                    item,
                                    item,
                                    5f,
                                    false,
                                    false
                                    )
                            rebalanceWeights(selectedFunds)
                        }
                    )
                }
            }

            LazyColumn(modifier = Modifier.padding(paddingValues), content = {
                item {
                selectedFunds.forEach { entry ->
                        selectedWeight =entry.value.weight
                        Card(modifier = Modifier.fillMaxWidth()) {
                            LazyRow (modifier = Modifier
                                .fillMaxWidth() ,
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically){
                                    item{Text(text = entry.value.insName)}
                                    item{Text(text = entry.value.weight.roundToInt() .toString()+"%")}
                                    item{Switch(
                                        checked = entry.value.unlocked,
                                        onCheckedChange = {
                                            entry.value.unlocked = it
                                        })}
                                    item{ Button(onClick = {selectedFunds.remove(entry.key)},
                                              content={ Icon(imageVector = Icons.Filled.Close,
                                                         contentDescription ="Remove",)})
                            }
                            }
                        }
                    Slider(
                        value = selectedWeight,
                        onValueChange = { selectedWeight = it
                            entry.value.weight=selectedWeight},
                        onValueChangeFinished = {
                                rebalanceWeights(selectedFunds)

                        },
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.secondary,
                            activeTrackColor = MaterialTheme.colorScheme.secondary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        steps = 99,
                        valueRange = 0f..100f
                    )
                }
                }
            }
            )
        }
    }
}



fun rebalanceWeights(
    selectedFunds:SnapshotStateMap<String,InstrumentInfo>
){

    var unlockedAllocation =0f
    var lockedAllocation= 0f

    unlockedAllocation=0f
    lockedAllocation=0f
    selectedFunds.forEach { entry ->
        if(entry.value.unlocked){
            unlockedAllocation+=entry.value.weight
        }else{
            lockedAllocation+=entry.value.weight
        }
    }
    selectedFunds.forEach { entry ->
        if(entry.value.unlocked){
            entry.value.weight=entry.value.weight/unlockedAllocation*(100-lockedAllocation)
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

