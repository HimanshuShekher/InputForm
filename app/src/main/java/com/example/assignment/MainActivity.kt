package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.ui.theme.RechargeApplicationTheme
import com.example.assignment.view.PersonListScreen
import com.example.assignment.view.UserInputScreen
import com.example.viewmodel.SaveUserDataViewModel

class MainActivity : ComponentActivity() {
    private val saveUserDataViewModel: SaveUserDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RechargeApplicationTheme {
                Surface(color = Color.White) {
                    MyApp(this@MainActivity,saveUserDataViewModel)
                }
            }
        }
    }
}
@Composable
fun MyApp(mainActivity: MainActivity, saveUserDataViewModel: SaveUserDataViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            UserInputScreen(navController,saveUserDataViewModel,mainActivity)
        }
        composable("screen2") {
            PersonListScreen(navController,saveUserDataViewModel,mainActivity)
        }
    }
}