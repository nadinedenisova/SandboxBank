package com.example.sandboxbank.App.ui.mainscreen.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandboxbank.App.ui.designkit.mode.LightColorPalette


@Composable
fun SettingScreen(){
    ComposeSetting()
}


@Composable
fun ComposeSetting() {
    val context = LocalContext.current
    Column(){
        Row(modifier = Modifier.padding(top = 20.dp)){
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
            Text(modifier = Modifier.padding(top = 10.dp), text = "Мои профиль", fontSize = 26.sp)
        }
        Box(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(LightColorPalette.secondaryFixedDim),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        text = "Смена темы: светлый/темный",
                        fontWeight = FontWeight.Bold
                    )
                    var checked1 by remember { mutableStateOf(false) }

                    Switch(
                        checked = checked1,
                        onCheckedChange = {
                            checked1 = it
                        })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        text = "Смена языка: английский/русский",
                        fontWeight = FontWeight.Bold
                    )
                    var checked2 by remember { mutableStateOf(false) }

                    Switch(
                        checked = checked2,
                        onCheckedChange = {
                            checked2 = it
                        })
                }
            }
        }
        Box(
            Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Button(
                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                onClick = {},
            ){
                Text(text = "Выход из аккаунта")
            }
        }
    }

}