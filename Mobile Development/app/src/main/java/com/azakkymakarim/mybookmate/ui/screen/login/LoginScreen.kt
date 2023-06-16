package com.azakkymakarim.mybookmate.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.azakkymakarim.mybookmate.ui.theme.MyBookmateTheme
import com.azakkymakarim.mybookmate.ui.theme.background

@Composable
fun LoginScreen(navController: NavController) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(background)
                .padding(10.dp)
        ) {
            Text(
                text = "LOGIN",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                ),
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = emailValue.value,
                    onValueChange = { emailValue.value = it},
                    placeholder = { Text(text = "Email", color = Color.DarkGray)},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.LightGray),
                )
                Spacer(modifier = Modifier.padding(5.dp))
                TextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it},
                    placeholder = { Text(text = "Password", color = Color.DarkGray) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.LightGray)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp),
                ) {
                    Text(text = "LOGIN", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    MyBookmateTheme {
//        LoginScreen()
//    }
//}