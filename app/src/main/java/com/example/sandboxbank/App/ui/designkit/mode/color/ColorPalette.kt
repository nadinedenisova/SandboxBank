package com.example.sandboxbank.App.ui.designkit.mode

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val primary: Color,          // (Основной фон) Кнопка, текст в outline кнопке
    val primaryFixedVariant:Color,   // Заголовоки
    val primaryInverce: Color,  // Кнопка стереть в пинкоде, сумма вкладов, сроки кроедитов с вкладами
    val secondary: Color,        // ввод пинкода, иконки
    val secondaryContainer: Color,          //кнопка пинкода, текущий пункт navibar
    val secondaryFixedDim: Color,     //Мой профиль, пункты меню
    val tertiary: Color,
    val onTertiary: Color,

    val background:Color,     // Фон приложения
    val surface :Color,            // Светлая поверхность, navigationbar
    val surfaceContainerHigh :Color,
    val surfaceContainerHighest: Color, //неактивный переключатель
    val inverseSurface: Color,     //slider value indicator
    val inverseOnSurface: Color,

    val onPrimary:Color,          // Цвет текста на основном фоне
    val onPrimaryDark: Color,     //Успешный пин
    val onSecondary:Color,         // Черный цвет текста на вторичном фоне
    val onSecondaryContainer: Color,

    val onSurface:Color,         // Черный цвет текста на поверхности
    val onSurfaceOpacity12:Color,
    val onSurfaceContainer:Color,//Мой профиль, пункты меню
    val onSurfaceVariant:Color,  //Текст над и под textField, иконки и текст navigationbar
    val onError:Color,             // Цвет текста, обводки и иконок на фоне ошибок
    val outline: Color,             //обводка textfield, и outlinebutton
    val outlineVariant:Color,

    val creditColor:Color,
    val debitColor:Color,
    val card:Color,
    val cardBalanceLogo:Color,
    val dialogBackground:Color,
    val checkMarkcheckMark:Color,
)

val baseLightPalette = ColorPalette(
    primary = Color(0xFF65558F),            // (Основной фон) Кнопка, текст в outline кнопке
    primaryFixedVariant = Color(0xFF4F378B), // Заголовоки
    primaryInverce = Color(0xFF6750A4),    // Кнопка стереть в пинкоде, сумма вкладов, сроки кроедитов с вкладами
    secondary = Color(0xFF625B71),           // ввод пинкода, иконки
    secondaryContainer = Color(0xFFE8DEF8),           //кнопка пинкода, текущий пункт navibar
    secondaryFixedDim = Color(0xFFCCC2DC),         //Мой профиль, пункты меню
    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),//кнопка Выход из аккаунта

    // Нейтральные цвета
    background = Color(0xFFFFFFFF),          // Фон приложения
    surface = Color(0xFFFEF7FF),             // Светлая поверхность, navigationbar
    surfaceContainerHigh = Color(0xFFECE6F0),
    surfaceContainerHighest = Color(0xFFE6E0E9), //неактивный переключатель
    inverseSurface = Color(0xFF322F35),      //slider value indicator
    inverseOnSurface = Color(0xFFF5EFF7) ,     //slider value indicator

    // val error = Color(0xFFB00020)               // Красный цвет для ошибок
    onPrimary = Color(0xFFFFFFFF) ,           // Цвет текста на основном фоне
    onPrimaryDark = Color(0xFF381E72),       //Успешный пин
    onSecondary = Color(0xFF000000),          // Черный цвет текста на вторичном фоне
    onSecondaryContainer = Color(0xFF4A4459) ,         // Черный цвет текста на вторичном фоне

    //val onBackground = Color(0xFF000000)         // Черный цвет текста на фоне
    onSurface = Color(0xFF1D1B20)  ,          // Черный цвет текста на поверхности
    onSurfaceOpacity12 = Color(0x1D1B201F),
    onSurfaceContainer = Color(0xFFF3EDF7) , //Мой профиль, пункты меню
    onSurfaceVariant = Color(0xFF49454F)  ,  //Текст над и под textField, иконки и текст navigationbar
    onError = Color(0xFFB3261E)   ,           // Цвет текста, обводки и иконок на фоне ошибок
    outline = Color(0xFF79747E)    ,            //обводка textfield, и outlinebutton
    outlineVariant = Color(0xFFCAC4D0)  ,              //обводка textfield, и outlinebutton
    creditColor = Color(0xFFFFD7B6),
    debitColor = Color(0XFFD2F1E4),
    card = Color(0xFFDAEBFF),
    cardBalanceLogo = Color(0xFF222222),
    dialogBackground = Color(0x80797979),
    checkMarkcheckMark = Color(0xFF14AE5C),
)

val baseDarkPalette = baseLightPalette.copy(
    // Нейтральные цвета
    background = Color(0xFF000000),          // Фон приложения
    onSurface = Color(0xFFFFF9FF),
    surface = Color(0xFF141218),
    tertiary = Color(0xFFE2E2E2),
    onTertiary = Color(0xFF1A1C1C),
)

object LightColorPalette {
    // Основные цвета
    val primary = Color(0xFF65558F)            // (Основной фон) Кнопка, текст в outline кнопке
    val primaryFixedVariant = Color(0xFF4F378B)     // Заголовоки
    val primaryInverce = Color(0xFF6750A4)     // Кнопка стереть в пинкоде, сумма вкладов, сроки кроедитов с вкладами
    val secondary = Color(0xFF625B71)           // ввод пинкода, иконки
    val secondaryContainer = Color(0xFFE8DEF8)           //кнопка пинкода, текущий пункт navibar
    val secondaryFixedDim = Color(0xFFCCC2DC)         //Мой профиль, пункты меню
    val tertiary = Color(0xFF7D5260)
    val primary1 = Color(0xFF6B548D)    //активный переключатель
    val primary2 = Color(0xFFD0BCFE)//кнопка Выход из аккаунта

    // Нейтральные цвета
    val background = Color(0xFFFFFFFF)          // Фон приложения
    val surface = Color(0xFFFEF7FF)             // Светлая поверхность, navigationbar
    val surfaceContainerHigh = Color(0xFFECE6F0)
    val surfaceContainerHighest = Color(0xFFE6E0E9) //неактивный переключатель
    val inverseSurface = Color(0xFF322F35)      //slider value indicator
    val inverseOnSurface = Color(0xFFF5EFF7)      //slider value indicator

    // val error = Color(0xFFB00020)               // Красный цвет для ошибок
    val onPrimary = Color(0xFFFFFFFF)            // Цвет текста на основном фоне
    val onPrimaryDark = Color(0xFF381E72)       //Успешный пин
    val onSecondary = Color(0xFF000000)          // Черный цвет текста на вторичном фоне
    val onSecondaryContainer = Color(0xFF4A4459)          // Черный цвет текста на вторичном фоне

    //val onBackground = Color(0xFF000000)         // Черный цвет текста на фоне
    val onSurface = Color(0xFF1D1B20)            // Черный цвет текста на поверхности
    val onSurfaceOpacity12 = Color(0x1D1B201F)
    val onSurfaceContainer = Color(0xFFF3EDF7)  //Мой профиль, пункты меню
    val onSurfaceVariant = Color(0xFF49454F)    //Текст над и под textField, иконки и текст navigationbar
    val onError = Color(0xFFB3261E)              // Цвет текста, обводки и иконок на фоне ошибок
    val outline = Color(0xFF79747E)                //обводка textfield, и outlinebutton
    val outlineVariant = Color(0xFFCAC4D0)                //обводка textfield, и outlinebutton


    // Дополнительные цвета
    //val teal200 = Color(0xFF03DAC5)
    //val teal700 = Color(0xFF018786)
    //val blueGray = Color(0xFF607D8B)
    val creditColor = Color(0xFFFFD7B6)
    val debitColor = Color(0XFFD2F1E4)
    val card = Color(0xFFDAEBFF)
    val cardBalanceLogo = Color(0xFF222222)
    val dialogBackground = Color(0x80797979)
    val checkMarkcheckMark = Color(0xFF14AE5C)
}

object DarkColorPalette {
    // Основные цвета
    val primary = Color(0xFF65558F)     //выделенное поле ввода экрана авторизации, текст в главном меню
    val primary1 = Color(0xFF6B548D)    //активный переключатель
    val primary2 = Color(0xFFD0BCFE)

    val primary3 = Color(0xFFD3C1FF)    //Кнопка обновить приложение
    val onPrimary3 = Color(0xFFD3C1FF)    //Текст кнопки обновить приложение

    val inversePrimary = Color(0xFFD0BCFF)       //Кнопка на экране авторизации и регистрации, выделенное поле ввода на регистрации
    val onPrimary = Color(0xFFFFFFFF)            // Заголовоки,
    val primaryFixedVariant = Color(0xFF4F378B)     // Кредиты, кнопка внести платёж
    val primaryInverse = Color(0xFF6750A4)     // Кнопка стереть в пинкоде, сумма вкладов, сроки кроедитов с вкладами, биометрия пинкод
    val secondary2 = Color(0xFF625B71)          //Иконка в диалоге
    val secondary = Color(0xFF5F5E5F)           // ввод пинкода, иконки
    val secondaryFixed = Color(0xFFEBDDF7)      //Мой профиль, меню 1
    val secondaryFixedDim = Color(0xFFCFC1DB)      //Мой профиль, меню 3
    val secondaryContainer = Color(0xFFE8DEF8)           //кнопка пинкода, меню "История операций"
    val secondaryContainer2 = Color(0xFF4A4458)           //фон неактивного слайдера
    val tertiaryFixedDim = Color(0xFFEFB8C8)         //Предупреждение на экране Карты, и цвет кнопки закрыть карту
    val onTertiaryFixedVariant = Color(0xFF633B48)  //цвет текста кнопки закрыть карту
        val tertiary = Color(0xFFE2E2E2)            //обводка и текст кнопки Выход из аккаунта
    val onTertiary = Color(0xFF1A1C1C)          //кнопка Выход из аккаунта

    //
    //    // Нейтральные цвета
        val surface = Color(0xFFFEF7FF)             // мой профиль меню 2
    val surface1 = Color(0xFF151218)
    //    val surfaceContainerHigh = Color(0xFFECE6F0)
    val onSurfaceContainerHighest = Color(0xFFE6E0E9) //неактивный переключатель, пункт меню, заголовок "Открытие вклада"
    val onSurfaceContainerLowest = Color(0xFF0F0D13) //фон главного меню
    val surfaceContainer = Color(0xFF211F26) //navigationbar, меню
    val surfaceContainer2 = Color(0xFFF2ECF4)    //Открыте вкладов окно начисления процентов
    val surfaceContainer1 = Color(0xFFE7E0E8) //мой профиль меню 1 заголовок
    val surfaceContainerHigh = Color(0xFF2B292F) //История операций, дебетовая, фон истории
    val surfaceContainerHigh1 = Color(0xFFEAE7EF) //История операций, фон заглушки "нет операций"
    //    val inverseSurface = Color(0xFF322F35)      //slider value indicator
    //    val inverseOnSurface = Color(0xFFF5EFF7)      //slider value indicator
    //    // val error = Color(0xFFB00020)               // Красный цвет для ошибок
    //    val onPrimary = Color(0xFFFFFFFF)            // Цвет текста на основном фоне
    val onPrimaryFixed = Color(0xFF21005D)        //Цвет текста в кнопке экрана авторизации и регистрации
    val onPrimaryDark = Color(0xFF381E72)       //Успешный пин
    val onSecondaryFixed = Color(0xFFE0DDF6)          // "История операций" выбранный раздел
    val onSecondaryContainer = Color(0xFF4A4459)          //Цвет цифр пинкода
    val onBackground = Color(0xFF000000)         //Открыте вкладов окно начисления процентов текст
    val onSurface4 = Color(0xFF1C1B21)      //История операций, текст заглушки "нет операций"
    val onSurface1 = Color(0xFF1D1B20)            // Фон экрана авторизации, текст заголовка диалога, текст кнопки диалога
    val onSurface2 = Color(0xFF141218)      //Заголовок "мой профиль"
    val onSurface3 = Color(0xFF1D1A20)      //мой профиль меню 2 и 3 текст
    val onSurface = Color(0xFF1B1B1B)            // Черный цвет текста на поверхности
    //    val onSurfaceOpacity12 = Color(0x1D1B201F)
    val onSurfaceContainer = Color(0xFFF3EDF7)  //Мой профиль, пункты меню
    val onSurfaceVariant = Color(0xFFCAC4D0)    //элементы navigationbar
    val onSurfaceVariant2 = Color(0xFF49454F)   //Точки ввода пароля на Авторизации, текст описания диалога
    val onError = Color(0xFFB3261E)              // Цвет текста, обводки и иконок на фоне ошибок
    val outline = Color(0xFF938F99)                //обводка textfield, и outlinebutton
    val outline1 = Color(0xFF7A757F)         //неактивный переключатель
    val outlineVariant = Color(0xFFCEC8D4)                //обводка textfield, и outlinebutton
    //
    //
    //    // Дополнительные цвета
    //    //val teal200 = Color(0xFF03DAC5)
    //    //val teal700 = Color(0xFF018786)
    //    //val blueGray = Color(0xFF607D8B)
    val creditColor = Color(0xFFFFD7B6)
    val debitColor = Color(0XFFD2F1E4)
    val card = Color(0xFFDAEBFF)
    val cardBalanceLogo = Color(0xFF222222)
    val dialogBackground = Color(0x79797980) //фон диалога
    val checkMarkcheckMark = Color(0xFF14AE5C)
}

@Composable
fun selectColor(light: Color, dark: Color): Color{
    return if (isSystemInDarkTheme()){
        dark
    } else {
        light
    }
}
