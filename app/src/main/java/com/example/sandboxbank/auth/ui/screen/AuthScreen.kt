package com.example.sandboxbank.ui.auth

import com.example.sandboxbank.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import com.example.sandboxbank.mode.LightColorPalette
import com.example.sandboxbank.auth.ui.viewmodel.AuthViewModel


@Composable
fun AuthScreen(
    viewModel: AuthViewModel = viewModel(), onLoginSuccess: () -> Unit,
    onNavigateToRegistration: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val isFormValid = email.isNotBlank() && password.length >= 6
    val loginState by viewModel.authState.collectAsState()

    var errorMessage = remember(loginState) {
        if (loginState is ResultAuthState.Error) (loginState as ResultAuthState.Error).message else null
    }

    LaunchedEffect(loginState) {
        if (loginState is ResultAuthState.Success) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.auth),
            style = MaterialTheme.typography.headlineMedium,
            color = LightColorPalette.primary,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
                errorMessage = null
            },
            label = { Text(stringResource(R.string.email)) },
            isError = emailError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
                errorMessage = null
            },
            label = { Text(stringResource(R.string.password)) },
            isError = passwordError,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisible) painterResource(R.drawable.ic_password_hide) else painterResource(
                        R.drawable.ic_password_show
                    )
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(icon, contentDescription = null)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            if (passwordError) {
                Text(
                    text = stringResource(R.string.wrong_pwd_email),
                    style = MaterialTheme.typography.labelSmall,
                    color = LightColorPalette.onError
                )
            } else {
                Text(
                    text = stringResource(R.string.hint_six_symbols),
                    style = MaterialTheme.typography.labelSmall,
                    color = LightColorPalette.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                emailError = email.isBlank() || !email.contains("@") || !email.contains(".")
                passwordError = password.length < 6

                if (!emailError && !passwordError) {
                    viewModel.login(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = LightColorPalette.primary ,
                disabledContainerColor = LightColorPalette.secondaryContainer
            )
        ) {
            Text(stringResource(R.string.enter), color = LightColorPalette.background)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { onNavigateToRegistration() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            border = BorderStroke(1.dp, LightColorPalette.onSurfaceVariant),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = LightColorPalette.primary)
        ) {
            Text(stringResource(R.string.registration))
        }
    }
}