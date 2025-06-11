package com.example.sandboxbank.mainscreen.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingScreen(){
    ComposeSwitch()
}


@Composable
fun ComposeSwitch() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        IconButton(
            onClick = {
                (context as? ComponentActivity)?.finish()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",Modifier.size(34.dp)
            )
        }
        Text(text = "Settings", fontSize = 26.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Dark Mode",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Changing mode of app", color = Color.DarkGray)
            }
            var checked1 by remember { mutableStateOf(false) }

            Switch(
                checked = checked1,
                onCheckedChange = {
                    checked1 = it
                })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column {
                Text(
                    text = "Language",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Choose your language", color = Color.DarkGray)
            }
            Text(
                modifier = Modifier
                    .clickable { println("Change") }
                    .padding(top = 10.dp),
                text = "English",
                fontWeight = FontWeight.Bold
            )
        }
    }
}