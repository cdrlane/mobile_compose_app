package com.example.mobile_compose_app

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import com.example.mobile_compose_app.ui.theme.clc_clay
import com.example.mobile_compose_app.ui.theme.clc_navy


private fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
    (value - min) / (max - min)

@Composable
fun PerformanceChart(modifier: Modifier, list: List<Float> = listOf(10f, 20f, 3f, 1f)) {
    val zipList: List<Pair<Float, Float>> = list.zipWithNext()

    Row(modifier = modifier) {
        val max = list.max()
        val min = list.min()

        val lineColor =
            if (list.last() > list.first()) clc_navy else clc_clay // <-- Line color is Green if its going up and Red otherwise

        for (pair in zipList) {

            val fromValuePercentage = getValuePercentageForRange(pair.first, max, min)
            val toValuePercentage = getValuePercentageForRange(pair.second, max, min)

            Canvas(
                modifier = Modifier.
                    fillMaxSize()
                    .weight(1f),
                onDraw = {
                    val fromPoint = Offset(x = 0f, y = size.height.times(1 - fromValuePercentage)) // <-- Use times so it works for any available space
                    val toPoint =
                        Offset(x = size.width, y = size.height.times(1 - toValuePercentage)) // <-- Also here!

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

@Composable
fun LineChart(){

    val steps=5
    val pointsData= listOf(
        Point(0f, 40f),
        Point(2f,10f),
        Point(3f,-30f),
        Point(4f,56f),
        Point(6f,21f)
    )

    val xAxisData= AxisData.Builder()


}