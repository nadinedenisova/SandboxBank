package com.example.sandboxbank.App.ui.designkit.mode.language

val RUSSIAN = Language.RUS

val supportedLocalesNow = registerSupportedLocales(Language.RUS)

val myProfile = Translatable(
    "My profile",
    hashMapOf(RUSSIAN to "Мои профиль")
)

val changeTheme = Translatable(
    "Change theme: light/dark",
    hashMapOf(RUSSIAN to "Смена темы: светлый/темный")
)

val changeLanguage = Translatable(
    "Change language: russian/english",
    hashMapOf(RUSSIAN to "Смена языка: русский/английский")
)

val exitProfile = Translatable(
    "Exit from account",
    hashMapOf(RUSSIAN to "Выход из аккаунта")
)


