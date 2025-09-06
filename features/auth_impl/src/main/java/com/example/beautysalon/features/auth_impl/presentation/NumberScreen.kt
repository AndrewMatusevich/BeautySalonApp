package com.example.beautysalon.features.auth_impl.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberScreen(sendOtp : (phoneNumber: String) -> Unit) {

    var phone by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Номер телефона") }
            )
            Button(onClick = { sendOtp(phone) }) {
                Text("Отправить OTP")
            }
        }
    }
}
// +1 330-555-8831
// +375292689007