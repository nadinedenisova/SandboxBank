package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette
import com.example.sandboxbank.App.ui.mainscreen.data.Routes
import com.example.sandboxbank.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        backgroundColor = Color(0xF3, 0xED, 0xF7)
    ) {
        Routes.entries
            .filter { it.icon != null }
            .forEach { it ->
                BottomNavigationItem(
                    modifier = Modifier.padding(bottom = 15.dp).weight(1f),
                    icon = { Icon(ImageVector.vectorResource(it.icon!!), contentDescription = "") },
                    label = {
                        BasicText(
                            text = it.title(),
                            style = TextStyle(
                                color = LightColorPalette.onSurfaceVariant,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.roboto)),
                                fontWeight = FontWeight(500)
                            ),
                            softWrap = false,
                        )
                    },
                    selected = currentDestination?.route == it.route,
                    onClick = { navController.navigate(it.route) }
                )
            }
    }
}
