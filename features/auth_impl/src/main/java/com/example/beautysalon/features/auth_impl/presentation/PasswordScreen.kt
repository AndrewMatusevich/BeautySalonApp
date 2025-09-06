package com.example.beautysalon.features.auth_impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PasswordScreen(verifyOtp : (code: String) -> Unit) {

    var code by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        TextField(value = code, onValueChange = { code = it }, label = { Text("Введите код") })
        Button(
            onClick = { verifyOtp(code) }
        ) {
            Text("Подтвердить OTP") }
    }
}