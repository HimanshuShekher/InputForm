package com.example.assignment.view


import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment.MainActivity
import com.example.model.UserDataClass
import com.example.viewmodel.SaveUserDataViewModel

@Composable
fun PersonListScreen(
    navController: NavHostController,
    saveUserDataViewModel: SaveUserDataViewModel,
    mainActivity: MainActivity
) {
    saveUserDataViewModel.retriveUser()
    val persons = saveUserDataViewModel.userData.value


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(persons) { person ->
            ListItem(person)
        }
    }
}

@Composable
fun ListItem(person: UserDataClass) {
    Card(
        modifier = Modifier.fillMaxHeight()
            .fillMaxWidth().background(Color.White)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),


    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${person.name}", fontWeight = FontWeight.Bold)
            Text(text = "Age: ${person.age}")
            Text(text = "Address: ${person.address}")
            Text(text = "DOB: ${person.dob}")
        }
    }
}