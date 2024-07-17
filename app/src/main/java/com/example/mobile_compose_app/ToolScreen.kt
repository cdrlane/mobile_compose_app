package com.example.mobile_compose_app

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile_compose_app.composables.instrumentsHeader
import com.example.mobile_compose_app.ui.theme.clc_stone


@Composable
fun ToolScreen(toolsNavController: NavController,paddingValues: PaddingValues) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            Button(onClick = { toolsNavController.navigate("portfolio_constructor")},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
                    .height(60.dp)
                    .width(120.dp)) {
                Text("Portfolio Constructor")
            }
        }

        item {
            Button(onClick = { },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
                    .height(60.dp)
                    .width(120.dp)) {
                Text(text="Portfolio Optimiser")
            }
        }

    }
}

@Composable
fun PortfolioContructor(toolsNavController: NavController,paddingValues: PaddingValues) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {

            Text("Test")
        }




    }
}



