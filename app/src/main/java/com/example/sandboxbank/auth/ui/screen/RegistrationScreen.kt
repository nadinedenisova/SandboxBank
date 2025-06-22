package com.example.sandboxbank.auth.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.R
import com.example.sandboxbank.auth.domain.model.ResultAuthState
import com.example.sandboxbank.auth.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RegistrationScreen(
    viewModel: AuthViewModel = viewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope(),
    onBackClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isUserExists by remember { mutableStateOf(false) }
    var isIncorrectEmail by remember { mutableStateOf(false) }
    var isPasswordShort by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(true) }

    val state by viewModel.authState.collectAsState()

    LaunchedEffect(state) {
        when (state) {
            is ResultAuthState.Success -> {
                onRegisterSuccess()
            }
            is ResultAuthState.Error -> {
                when (val errorMessage = (state as ResultAuthState.Error).message) {
                    "Пользователь уже существует" -> {
                        isUserExists = true
                    }
                    "Некорректный email" -> {
                        isIncorrectEmail = true
                    }
                    "Пароль менее 7 символов" -> {
                        isPasswordShort = true
                    }
                    else -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = errorMessage,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }

    Column(modifier = Modifier
        .background(LightColorPalette.background)
        .fillMaxSize()
    ) {

        CustomTopBar(stringResource(R.string.auth), onBackClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.registration),
                color = LightColorPalette.primary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.height(64.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(stringResource(R.string.email)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                isError = isUserExists,
                trailingIcon = {
                    if (isUserExists) {
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = "error"
                        )
                    }
                }
            )

            if (isUserExists){
                Text(
                    modifier = Modifier
                        .height(20.dp)
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                    text = stringResource(R.string.userExists),
                    color = LightColorPalette.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                password = password,
                onPasswordChange = { newPassword ->
                    password = newPassword
                },
                isPasswordShort = isPasswordShort,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = {
                    passwordVisible = !passwordVisible
                }
            )

            Text(
                modifier = Modifier
                    .height(20.dp)
                    .align(Alignment.Start)
                    .padding(start = 16.dp),
                text = stringResource(R.string.hint_six_symbols),
                style = MaterialTheme.typography.bodySmall,
                color = if (isPasswordShort) LightColorPalette.error else LightColorPalette.onSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isButtonEnabled) {
                        isButtonEnabled = false
                        scope.launch {
                            viewModel.register(email, password)
                            delay(500)
                            isButtonEnabled = true
                        }
                    }
                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(100.dp),
            ) {
                Text(text = stringResource(R.string.register))
            }
        }
    }
}

@Composable
fun CustomTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(modifier = Modifier
        .height(64.dp)
        .fillMaxWidth()
    ) {

        Box (modifier = Modifier
            .size(48.dp, 48.dp)
            .align(Alignment.CenterVertically),
        )
        {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
                    .clickable { onBackClick() },
                painter = painterResource(R.drawable.ic_back_arrow),
                contentDescription = "Back"
            )
        }

        Text(
            modifier = Modifier
                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                .align(Alignment.CenterVertically),
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordShort: Boolean,
    passwordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.password)) },
        singleLine = true,
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        isError = isPasswordShort,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (isPasswordShort) {
                Icon(
                    painter = painterResource(R.drawable.ic_error),
                    contentDescription = "error"
                )
            } else {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Icon(
                        painter = if (passwordVisible) {
                            painterResource(R.drawable.ic_password_show)
                        } else {
                            painterResource(R.drawable.ic_password_hide)
                        },
                        contentDescription = "visible password"
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
