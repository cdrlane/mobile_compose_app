package com.example.mobile_compose_app.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.mobile_compose_app.R
import com.example.mobile_compose_app.ui.theme.LightCarmin
import com.example.mobile_compose_app.ui.theme.LightOlive
import com.example.mobile_compose_app.ui.theme.clc_clay
import com.example.mobile_compose_app.ui.theme.clc_navy
import com.example.mobile_compose_app.ui.theme.clc_stone


data class DropDownItem(
    val text:String
)

@Composable
fun InstrumentPerformanceCard(
    assetInfo: com.example.mobile_compose_app.AssetInfo,
    dropdownItems: List<DropDownItem>,
    modifier: Modifier = Modifier,
    onItemClick: (DropDownItem) -> Unit
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember {
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val density = LocalDensity.current


    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .onSizeChanged {
                itemHeight = with(density) { it.height.toDp() }
            }
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
        colors = CardDefaults.cardColors()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier

                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    )
                }
                .padding(16.dp)

        ) {
            AssetIcon(assetInfo.iconDrawable)

            TickerName(assetInfo.name, assetInfo.tickerName)

            PerformanceChart(
                Modifier
                    .height(40.dp)
                    .width(60.dp), assetInfo.lastDayChange
            )

            ValueView(assetInfo.currentValue, assetInfo.total,assetInfo.riskValue)
        }
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = {
                isContextMenuVisible = false
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropdownItems.forEach {
                DropdownMenuItem(
                                    text = {  Text(text = it.text) },
                                    onClick = { onItemClick(it)
                                                isContextMenuVisible=false
                })
              
            }
        }



    }
}


@Composable
fun ValueView(currentValue: Float, total: Float,riskValue:Int) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = currentValue.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "YTD: ${total}%",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            text = "Risk: $riskValue",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}

@Composable
@Preview(heightDp = 300, widthDp = 300, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun PerformanceChart(modifier: Modifier = Modifier, list: List<Float> = listOf(10f, 20f, 3f, 1f)) {
    val zipList: List<Pair<Float, Float>> = list.zipWithNext()

    Row(modifier = modifier) {
        val max = list.max()
        val min = list.min()

        val lineColor =
            if (list.last() > list.first())  LightOlive else LightCarmin

        for (pair in zipList) {

            val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
            val toValuePercentage = getValuePercentageForRange(pair.second, max, min)

            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onDraw = {
                    val fromPoint = Offset(x = 0f, y = size.height.times(1 - fromValuePercentage))
                    val toPoint =
                        Offset(x = size.width, y = size.height.times(1 - toValuePercentage))

                    drawLine(
                        color = lineColor,
                        start = fromPoint,
                        end = toPoint,
                        strokeWidth = 3f
                    )
                })
        }
    }
}

private fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
    (value - min) / (max - min)

@Composable
//@Preview
private fun TickerName(name: String = "Apple Inc.", tickerName: String = "AAPL",aum:Double=100.0) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 5.dp)
            .width(80.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = "AUM:$$aum", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }

}

@Composable
//@Preview
private fun AssetIcon(iconDrawable: Int = R.drawable.markets) {
    Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier, onDraw = {
            val radius = 65f
            drawCircle(
                color = Color.White,
                radius = radius
            )
        })
        Icon(
            painter = painterResource(id = iconDrawable),
            contentDescription = "Asset Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(35.dp)
                .padding(bottom = 3.dp)
        )
    }

}