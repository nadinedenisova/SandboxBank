package com.example.sandboxbank.cards.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sandboxbank.App.ui.debitcards.debit.ui.compose.items.NoInternetDialog
import com.example.sandboxbank.App.ui.designkit.mode.color.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.LanguageSingleton
import com.example.sandboxbank.App.ui.designkit.mode.language.debitCardOpen
import com.example.sandboxbank.App.ui.designkit.mode.language.creditCardOpen
import com.example.sandboxbank.App.ui.mainscreen.data.Routes
import com.example.sandboxbank.R
import com.example.sandboxbank.cardmanager.cards.debit.ui.compose.items.CardView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    navController: NavController,
    viewModel: CardsScreenViewModel,
    ) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCards()
    }

    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ColorSingleton.appPalette.value.surface)
        ) {
            when (val state = uiState.value) {
                is CardsListUIState.Loading -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is CardsListUIState.Error -> {
                    NoInternetDialog(
                        onDismiss = {navController.popBackStack()},
                        onRetry = { viewModel.retryLoading() }
                    )
                }
                is CardsListUIState.Success, is CardsListUIState.SuccessEmpty -> {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CardView()
                        // Дебетовая карта
                        Button(
                            onClick = { navController.navigate(Routes.DebitCardOpen.route) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF3EDF7)
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.icon_debit),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp),
                                )
                                Text(LanguageSingleton.localization.value.debitCardOpen(), color = Color(0xff65558F))
                            }
                        }

                        // Дебетовая карта
                        Button(
                            onClick = { navController.navigate(Routes.CreditCardOpen.route) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF3EDF7)
                            ),
                            shape = RoundedCornerShape(18.dp),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.icon_credit),
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp),
                                )
                                Text(LanguageSingleton.localization.value.creditCardOpen(), color = Color(0xff65558F))
                            }
                        }
                    }


                }
            }
        }
    }
}