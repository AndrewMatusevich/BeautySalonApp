package com.example.beautysalon.features.auth_impl.presentation

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.beautysalon.features.auth_impl.presentation.AuthState
import com.example.beautysalon.features.auth_impl.presentation.AuthViewModel
import com.example.beautysalon.features.auth_impl.presentation.Role
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel(), navController: NavHostController) {
    val state by viewModel.authState.collectAsState()
    var phone by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        if (state is AuthState.Idle || state is AuthState.CodeSent) {

            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Номер телефона") }
                )

                val context = LocalContext.current
                val activity = context as? Activity  // безопасное приведение

                Button(onClick = {
                    activity?.let { viewModel.sendOtp(phone, it) }
                }) {
                    Text("Отправить OTP")
                }
            }
        }
    }

        if (state is AuthState.CodeSent) {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(value = code, onValueChange = { code = it }, label = { Text("Введите код") })
            Button(onClick = {
                viewModel.verifyOtp((state as AuthState.CodeSent).verificationId, code)
            }) { Text("Подтвердить OTP") }
            }
        }

        if (state is AuthState.Error) {
            Text(text = (state as AuthState.Error).message, color = Color.Red)
        }

        if (state is AuthState.Authorized) {
            when ((state as AuthState.Authorized).role) {
                Role.ADMIN -> LaunchedEffect(Unit) { navController.navigate("adminScreen") }
                Role.MASTER -> LaunchedEffect(Unit) { navController.navigate("masterScreen") }
                Role.CLIENT -> LaunchedEffect(Unit) { navController.navigate("clientScreen") }
            }
        }
    }
// SMS unable to be sent until this region enabled by the app developer
// +1 330-555-8831
// +375292689007