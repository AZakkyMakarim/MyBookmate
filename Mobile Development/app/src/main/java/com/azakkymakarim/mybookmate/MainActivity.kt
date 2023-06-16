package com.azakkymakarim.mybookmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.azakkymakarim.mybookmate.ui.screen.login.LoginScreen
import com.azakkymakarim.mybookmate.ui.theme.MyBookmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookmateTheme {
                MyBookmateApp()
            }
        }
    }
}
