package com.example.assignment.view

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment.MainActivity
import com.example.viewmodel.SaveUserDataViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputScreen(
    navController: NavHostController,
    saveUserDataViewModel: SaveUserDataViewModel,
    context: MainActivity
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                maxLines = 1,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(

                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton( modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val datePickerDialog = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            dob = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }) {
                Text(if (dob.isEmpty()) "Select Date of Birth" else dob)
            }

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {

                    if (name.isEmpty()){
                        Toast.makeText(context,"Enter Name", Toast.LENGTH_SHORT).show()
                    }else if (age.isEmpty()){
                        Toast.makeText(context,"Enter Age", Toast.LENGTH_SHORT).show()

                    }else if (dob.isEmpty()){
                        Toast.makeText(context,"Select DOB", Toast.LENGTH_SHORT).show()

                    }else if (address.isEmpty()){
                        Toast.makeText(context,"Enter Address", Toast.LENGTH_SHORT).show()

                    }else{
                        try {
                            saveUserDataViewModel.insertUser(name,age,dob,address)
                            Toast.makeText(context,"Data saved successfully", Toast.LENGTH_SHORT).show()

                        }
                        catch (e:Exception){
                            Toast.makeText(context," $e", Toast.LENGTH_SHORT).show()

                        }

                    }
                    // Handle form submission
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .fillMaxWidth()
            ) {
                Text("Submit")
            }

            Button(onClick = { navController.navigate("screen2") }) {

                Text(text = "History")
                
            }
        }
    }
}