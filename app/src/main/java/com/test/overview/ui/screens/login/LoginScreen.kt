package com.test.overview.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.overview.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.login),
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = MaterialTheme.colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginTextField(
            stringResource(id = R.string.username),
            viewModel.username,
            onValueChanged = {
                viewModel.showUsernameError = false
                viewModel.username = it
            },
            isError = viewModel.showUsernameError,
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginTextField(
            stringResource(id = R.string.password),
            viewModel.password,
            onValueChanged = {
                viewModel.showPasswordError = false
                viewModel.password = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true,
            isError = viewModel.showPasswordError
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(50.dp, 0.dp, 50.dp, 0.dp)) {
            Button(
                onClick = onLoginClick,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("LoginButtonTestTag"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                )

            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isPassword: Boolean = false,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val visualTransformation =
        if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier.testTag("${label}FieldTestTag"),
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = "$label is incorrect",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.tertiary
        ),

        )
}