package com.example.mobile_compose_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_compose_app.ui.theme.clc_navy

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context= LocalContext.current.applicationContext

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(horizontal = 26.dp, vertical = 140.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painterResource(id = R.drawable.small_logo), contentDescription ="CLC logo" )
        OutlinedTextField(value = username, onValueChange = {username=it},
            label={ Text(text="Username") },
            shape= RoundedCornerShape(20.dp),
            colors= TextFieldDefaults.colors(
                focusedLeadingIconColor = clc_navy,
                unfocusedLeadingIconColor = clc_navy,
                focusedLabelColor = clc_navy,
                unfocusedLabelColor = clc_navy,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = clc_navy,
                unfocusedIndicatorColor = clc_navy,
                unfocusedPlaceholderColor = clc_navy
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(value = password, onValueChange = {password=it},
            label={ Text(text="Password") },
            shape= RoundedCornerShape(20.dp),
            colors= TextFieldDefaults.colors(
                focusedLeadingIconColor = clc_navy,
                unfocusedLeadingIconColor = clc_navy,
                focusedLabelColor = clc_navy,
                unfocusedLabelColor = clc_navy,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = clc_navy,
                unfocusedIndicatorColor = clc_navy,
                unfocusedPlaceholderColor = clc_navy
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
           onLoginSuccess()
        },
            colors= ButtonDefaults.buttonColors(clc_navy),
            contentPadding = PaddingValues(start=60.dp,end=60.dp,top=8.dp,bottom=8.dp),
            modifier = Modifier.padding(top=18.dp))
        {
            Text(text="Login",fontSize=22.sp)
        }

    }
}