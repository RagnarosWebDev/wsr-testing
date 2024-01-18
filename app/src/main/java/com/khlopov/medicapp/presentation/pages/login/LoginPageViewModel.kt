package com.khlopov.medicapp.presentation.pages.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khlopov.medicapp.data.entity.SendCodeDto
import com.khlopov.medicapp.data.entity.UserLoginDto
import com.khlopov.medicapp.domain.repository.AppStoreInfoRepository
import com.khlopov.medicapp.domain.repository.UserRepository
import com.khlopov.medicapp.utils.isEmailValid

class LoginPageViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData("")
    private val _code: MutableLiveData<String> = MutableLiveData("")

    val email: LiveData<String> get() = _email
    val code: LiveData<String> get() = _code

    val isEmailValid: Boolean get() = isEmailValid(_email.value!!)

    fun changeEmail(email: String) {
        _email.value = email;
    }

    suspend fun sendCode(): Pair<Boolean, String> {
        return try {
            Pair(true, userRepository.sendCode(SendCodeDto(email.value!!)))
        } catch (e: Exception) {
            Pair(false, "Ошибка запроса")
        }
    }

    suspend fun login(code: String): Pair<Boolean, String> {
        _code.value = code
        return try {
            val token = userRepository.login(UserLoginDto(_email.value!!, _code.value!!))
            Log.println(Log.ERROR, "asdasd", token.token);
            Pair(true, "Успешный вход")
        } catch (e: Exception) {
            Pair(false, "Код неверный")
        }
    }
}