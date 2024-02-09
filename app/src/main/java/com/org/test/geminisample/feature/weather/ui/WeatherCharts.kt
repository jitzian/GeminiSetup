package com.org.test.geminisample.feature.weather.ui

import android.graphics.Typeface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.LegendLabel
import co.yml.charts.common.model.LegendsConfig
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.org.test.geminisample.feature.weather.state.UIState

@Composable
fun WeatherCharts(
    modifier: Modifier = Modifier,
    state: UIState.Success,
) {

    val steps = 5
    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(state.pointsDataLocation1.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = state.pointsDataLocation1.minOf { it.y }
            val yMax = state.pointsDataLocation1.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()
    val colorPaletteList = listOf<Color>(Color.Blue, Color.Yellow, Color.Magenta, Color.DarkGray)
    val legendsConfig = LegendsConfig(
        //legendLabelList = DataUtils.getLegendsLabelData(colorPaletteList),
        legendLabelList = listOf(
            LegendLabel(
                name = "Loc 1",
                color = colorPaletteList.first()
            ),
            LegendLabel(
                name = "Loc 2",
                color = colorPaletteList[1]
            ),
            LegendLabel(
                name = "Loc 3",
                color = colorPaletteList[2]
            ),
        ),
        gridColumnCount = 4
    )

    Column(modifier = modifier.fillMaxWidth()) {
        val data = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(

                    Line(
                        dataPoints = state.pointsDataLocation1.take(50),
                        lineStyle = LineStyle(
                            lineType = LineType.SmoothCurve(isDotted = true),
                            color = colorPaletteList.first()
                        ),
                        shadowUnderLine = ShadowUnderLine(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Green,
                                    Color.Transparent
                                )
                            ), alpha = 0.3f
                        ),
                        selectionHighlightPoint = SelectionHighlightPoint(
                            color = Color.Green
                        ),
                        selectionHighlightPopUp = SelectionHighlightPopUp(
                            backgroundColor = Color.Black,
                            backgroundStyle = Stroke(2f),
                            labelColor = Color.Red,
                            labelTypeface = Typeface.DEFAULT_BOLD
                        )
                    ),

                    /*Line(
                        dataPoints = state.pointsDataLocation2.subList(0, 5),
                        lineStyle = LineStyle(
                            lineType = LineType.SmoothCurve(),
                            color = colorPaletteList[1]
                        ),
                        intersectionPoint = IntersectionPoint(color = Color.Red),
                        selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                            val xLabel = "x : ${(1900 + x).toInt()} "
                            val yLabel = "y : ${String.format("%.2f", y)}"
                            "$xLabel $yLabel"
                        })
                    ),*/

                    /*Line(
                        dataPoints = state.pointsDataLocation2.subList(0, 20),
                        LineStyle(color = colorPaletteList[2]),
                        IntersectionPoint(),
                        SelectionHighlightPoint(),
                        shadowUnderLine = ShadowUnderLine(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Cyan,
                                    Color.Blue
                                )
                            ), alpha = 0.5f
                        ),
                        SelectionHighlightPopUp()
                    ),

                    Line(
                        dataPoints = state.pointsDataProjection,
                        LineStyle(color = colorPaletteList[3]),
                        IntersectionPoint(),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp()
                    )*/

                    Line(
                        dataPoints = state.pointsDataLocation2.take(50),
                        lineStyle = LineStyle(
                            lineType = LineType.SmoothCurve(),
                            color = colorPaletteList[1]
                        ),
                        intersectionPoint = IntersectionPoint(color = Color.Red),
                        selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                            //val xLabel = "x : ${(1900 + x).toInt()} "
                            val xLabel = "x : ${x.toInt()} "
                            val yLabel = "y : ${String.format("%.2f", y)}"
                            "$xLabel $yLabel"
                        })
                    ),

                    Line(
                        dataPoints = state.pointsDataProjection.take(50),
                        LineStyle(color = colorPaletteList[2]),
                        IntersectionPoint(),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp()
                    )


                )
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines()
        )
        Column(modifier = Modifier.height(400.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                lineChartData = data
            )

            Legends(legendsConfig = legendsConfig)
//            Legends(legendsConfig = legendsConfig)


        }
    }
}