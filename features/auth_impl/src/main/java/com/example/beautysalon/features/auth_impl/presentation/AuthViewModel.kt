package com.example.beautysalon.features.auth_impl.presentation

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun sendOtp(phoneNumber: String, activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithPhone(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    _authState.value = AuthState.Error(e.message ?: "Ошибка")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    _authState.value = AuthState.CodeSent(verificationId)
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(verificationId: String, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhone(credential)
    }

    private fun signInWithPhone(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                val phone = result.user?.phoneNumber
                if (phone != null) {
                    checkRole(phone)
                } else {
                    _authState.value = AuthState.Error("Номер телефона не найден")
                }
            }
            .addOnFailureListener { e ->
                _authState.value = AuthState.Error(e.message ?: "Ошибка авторизации")
            }
    }

    private fun checkRole(phone: String) {
        db.collection("roles").document(phone)
            .get()
            .addOnSuccessListener { doc ->
                val role = doc.getString("role")
                when (role) {
                    "admin" -> _authState.value = AuthState.Authorized(Role.ADMIN)
                    "master" -> _authState.value = AuthState.Authorized(Role.MASTER)
                    else -> _authState.value = AuthState.Authorized(Role.CLIENT)
                }
            }
            .addOnFailureListener {
                _authState.value = AuthState.Authorized(Role.CLIENT)
            }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Authorized(val role: Role) : AuthState()
    data class Error(val message: String) : AuthState()
}

enum class Role { ADMIN, MASTER, CLIENT }

// +375292689007
