package com.example.beautysalon.features.auth_impl.presentation

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.beautysalon.domain_impl.usecases.DeleteUserUseCase
import com.example.beautysalon.domain_impl.usecases.GetUserUseCase
import com.example.beautysalon.domain_models.User
import com.example.beautysalon.domain_models.enums.Role
import com.example.beautysalon.features.auth_impl.domain.SaveUserUseCase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

// Состояния авторизации
sealed class AuthState {
    data object Loading : AuthState()      // загрузка / проверка данных
    data object Idle : AuthState()         // пользователь не авторизован
    data class CodeSent(val verificationId: String) : AuthState()
    data class Authorized(val role: Role) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel (
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        checkIfUserLoggedIn()
    }

    private fun checkIfUserLoggedIn() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val phone = currentUser.phoneNumber
            if (phone != null) {
                // ✅ Сначала проверяем локально
                restoreUser(phone)
            } else {
                _authState.value = AuthState.Error("Не удалось получить номер")
            }
        } else {
            _authState.value = AuthState.Idle // показываем экран логина
        }
    }

    /**
     * Восстановление пользователя из DataStore
     */
    private fun restoreUser(phone: String) {
        viewModelScope.launch {
            getUserUseCase.execute().collect{ localUser ->
                if (localUser != null) {
                    // ✅ Пользователь найден локально → используем его данные
                    _authState.value = AuthState.Authorized(localUser.role)
                } else {
                    // ❌ Нет локального пользователя → идём во Firestore
                    checkRole(phone)
                }
            }

        }
    }

    /**
     * Проверка роли в Firestore (и сохранение в DataStore)
     */
    private fun checkRole(phone: String) {
        val userDoc = db.collection("roles").document(phone)

        userDoc.get().addOnSuccessListener { doc ->
            viewModelScope.launch {
                if (!doc.exists()) {
                    // Новый пользователь → роль CLIENT
                    userDoc.set(mapOf("role" to "client"))
                    val user = User(phone = phone, role = Role.CLIENT)
                    saveUserUseCase.execute(user) // ✅ сохраняем локально
                    _authState.value = AuthState.Authorized(Role.CLIENT)
                } else {
                    val roleStr = doc.getString("role")
                    val role = when (roleStr) {
                        "admin" -> Role.ADMIN
                        "master" -> Role.MASTER
                        else -> Role.CLIENT
                    }
                    val user = User(phone = phone, role = role)
                    saveUserUseCase.execute(user) // ✅ сохраняем локально
                    _authState.value = AuthState.Authorized(role)
                }
            }
        }.addOnFailureListener {
            // Ошибка чтения → дефолт в CLIENT
            viewModelScope.launch {
                val user = User(phone = phone, role = Role.CLIENT)
                saveUserUseCase.execute(user) // ✅ тоже сохраняем
                _authState.value = AuthState.Authorized(Role.CLIENT)
            }
        }
    }


    /**
     * Отправка OTP
     */
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

    /**
     * Проверка кода OTP
     */
    fun verifyOtp(verificationId: String, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhone(credential)
    }

    /**
     * Логин через credential
     */
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
    /**
     * Logout
     */
    fun logout() {
        viewModelScope.launch {
            auth.signOut()
            deleteUserUseCase.execute()
            _authState.value = AuthState.Idle
        }
    }
}
class AuthViewModelFactory @Inject constructor (
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            saveUserUseCase = saveUserUseCase,
            getUserUseCase = getUserUseCase,
            deleteUserUseCase = deleteUserUseCase
        ) as T
    }
}





// +375292689007
