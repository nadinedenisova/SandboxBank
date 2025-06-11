package com.example.sandboxbank

import androidx.compose.ui.graphics.Color

object LightColorPalette {
    // Основные цвета
    val primary = Color(0xFF65558F)            // (Основной фон) Кнопка, текст в outline кнопке
    val primaryFixedVariant = Color(0xFF4F378B)     // Заголовоки
    val primaryInverce = Color(0xFF6750A4)     // Кнопка стереть в пинкоде
    val secondary = Color(0xFF625B71)           // ввод пинкода, иконки
    val secondaryContainer = Color(0xFFE8DEF8)           //кнопка пинкода, текущий пункт navibar
    val secondaryFixedDim = Color(0xFFCCC2DC)         //Мой профиль, пункты меню
    val tertiary = Color(0xFF7D5260)            //кнопка Выход из аккаунта

    // Нейтральные цвета
    val background = Color(0xFFFFFFFF)          // Фон приложения
    val surface = Color(0xFFFEF7FF)             // Светлая поверхность, navigationbar
    val surfaceContainerHigh = Color(0xFFECE6F0)
    val surfaceContainerHighest = Color(0xFFE6E0E9) //неактивный переключатель
   // val error = Color(0xFFB00020)               // Красный цвет для ошибок
    val onPrimary = Color(0xFFFFFFFF)            // Цвет текста на основном фоне
    val onPrimaryDark = Color(0xFF381E72)       //Успешный пин
    //val onSecondary = Color(0xFF000000)          // Черный цвет текста на вторичном фоне
    val onSecondaryContainer = Color(0xFF4A4459)          // Черный цвет текста на вторичном фоне
    //val onBackground = Color(0xFF000000)         // Черный цвет текста на фоне
    val onSurface = Color(0xFF1D1B20)            // Черный цвет текста на поверхности
    val onSurfaceContainer = Color(0xFFF3EDF7)  //Мой профиль, пункты меню
    val onSurfaceVariant = Color(0xFF49454F)    //Текст над и под textField, иконки и текст navigationbar
    val onError = Color(0xFFB3261E)              // Цвет текста, обводки и иконок на фоне ошибок
    val outline = Color(0xFF79747E)                //обводка textfield, и outlinebutton

    // Дополнительные цвета
    //val teal200 = Color(0xFF03DAC5)
    //val teal700 = Color(0xFF018786)
    //val blueGray = Color(0xFF607D8B)
    val creditColor = Color(0xFFFFD7B6)
    val debitColor = Color(0XFFD2F1E4)
    val card = Color(0xFFDAEBFF)
    val cardBalanceLogo = Color(0xFF222222)
    val dialogBackground = Color(0x80797979)
}