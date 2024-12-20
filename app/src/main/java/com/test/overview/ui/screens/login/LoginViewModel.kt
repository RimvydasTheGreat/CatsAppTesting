package com.test.overview.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.test.overview.model.User

class LoginViewModel : ViewModel() {
    var showUsernameError: Boolean by mutableStateOf(false)
    var showPasswordError: Boolean by mutableStateOf(false)

    var username: String by mutableStateOf("")
    var password: String by mutableStateOf("")

    fun loginValidation(): Boolean {
        val user = User(username, password)
        showPasswordError = password != user1.password && password != user2.password
        showUsernameError = username != user1.userName && username != user2.userName

        password = ""
        return user == user1 || user == user2
    }
}

val user1 = User(
    "user",
    "password"
)

val user2 = User(
    "user1",
    "password"
)
