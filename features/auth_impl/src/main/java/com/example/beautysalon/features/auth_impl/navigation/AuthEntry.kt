package com.example.beautysalon.features.auth_impl.navigation

import android.app.Activity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.beautysalon.features.auth_impl.presentation.AuthState
import com.example.beautysalon.features.auth_impl.presentation.AuthViewModel
import com.example.beautysalon.features.auth_impl.presentation.AuthViewModelFactory
import com.example.beautysalon.features.auth_impl.presentation.NumberScreen
import com.example.beautysalon.features.auth_impl.presentation.PasswordScreen
import com.example.beautysalon.ui.widgets.CustomProgressIndicator

@Composable
fun AuthEntry(
    viewModelFactory: AuthViewModelFactory,
    navigateTo: (String) -> Unit
) {
    val viewModel: AuthViewModel = viewModel(factory = viewModelFactory)
    val state by viewModel.authState.collectAsState()

    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(state) {
        if (state is AuthState.Authorized) {
            val role = (state as AuthState.Authorized).role
            navigateTo(role.roleName)
        }
    }

    when (state) {
        is AuthState.Loading -> CustomProgressIndicator()
        is AuthState.Idle -> NumberScreen{ phoneNumber ->
            activity?.let { viewModel.sendOtp(phoneNumber = phoneNumber, activity = activity) }
        }
        is AuthState.CodeSent -> PasswordScreen{ code ->
                viewModel.verifyOtp((state as AuthState.CodeSent).verificationId, code)
            }
        is AuthState.Error -> Text("Ошибка: ${(state as AuthState.Error).message}", color = Color.Red)
        is AuthState.Authorized -> CustomProgressIndicator()
    }
}


