package com.example.sandboxbank.profile.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sandboxbank.App.ui.designkit.mode.ColorSingleton
import com.example.sandboxbank.App.ui.designkit.mode.baseDarkPalette
import com.example.sandboxbank.App.ui.designkit.mode.baseLightPalette
import com.example.sandboxbank.R
import com.example.sandboxbank.profile.data.Language
import com.example.sandboxbank.profile.data.ProfileState
import com.example.sandboxbank.profile.domain.ProfileScreenViewModel


@Composable
fun ProfileScreen(viewModel: ProfileScreenViewModel){
    ComposeSetting(viewModel)
}


@Composable
fun ComposeSetting(viewModel: ProfileScreenViewModel) {
    val settingState = viewModel.profileState.collectAsState()
    var theme by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(false) }

    LaunchedEffect(settingState) {
        when(settingState.value){
            is ProfileState.CurrentSets -> {
                theme = (settingState.value as ProfileState.CurrentSets).isDark
                when((settingState.value as ProfileState.CurrentSets).language) {
                    Language.RUS -> language = false
                    Language.ENG -> language = true
                }
            }
            else -> {}
        }
    }

    when(settingState.value){
        is ProfileState.CurrentSets -> {
        }
        is ProfileState.Language ->{
            println(viewModel.profileState.collectAsState().value)
        }

        is ProfileState.Theme -> {
            theme = (settingState.value as ProfileState.Theme).isDark
            if((settingState.value as ProfileState.Theme).isDark){
                ColorSingleton.appPalette = baseDarkPalette
            }
            else{
                ColorSingleton.appPalette = baseLightPalette
            }
        }
    }


    val context = LocalContext.current
    Column(Modifier.background(ColorSingleton.appPalette.background)
        .fillMaxHeight()){
        Box(modifier = Modifier.fillMaxWidth().height(15.dp).background(ColorSingleton.appPalette.surface))
        Row(modifier = Modifier.background(ColorSingleton.appPalette.surface)){
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(contentColor = ColorSingleton.appPalette.onSurface),
                onClick = {
                    (context as? ComponentActivity)?.finish()
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_back_arrow_16x16),
                    contentDescription = "back",
                    Modifier.size(34.dp),
                )
            }
            Text(modifier = Modifier.padding(top = 10.dp)
                .fillMaxWidth(), color = ColorSingleton.appPalette.onSurface, text = "Мои профиль", fontSize = 26.sp)
        }
        Box(modifier = Modifier.fillMaxWidth().height(15.dp).background(ColorSingleton.appPalette.surface))
        Box(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(baseLightPalette.secondaryFixedDim),
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

                    Switch(
                        checked = theme,
                        onCheckedChange = {
                           viewModel.onEvent(ProfileEvent.SetTheme(it))
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

                    Switch(
                        checked = language,
                        onCheckedChange = {
                            language = it
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
                border = BorderStroke(1.dp, ColorSingleton.appPalette.tertiary),
                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = ColorSingleton.appPalette.onTertiary, contentColor = ColorSingleton.appPalette.tertiary),
                onClick = {},
            ){
                Text(text = "Выход из аккаунта")
            }
        }
    }


}

sealed interface ProfileEvent{
    data class SetTheme(val theme: Boolean): ProfileEvent
    data class SetLanguage(val lang: Boolean): ProfileEvent
}