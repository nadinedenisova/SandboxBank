package com.example.sandboxbank.App.ui.designkit.mode.language


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

internal val defaultLocalization: Localization = Localization(Language.ENG)

internal val localizationMap = hashMapOf<Language, Localization>()

data class Localization(
    val locale: Language,
    internal val strings: MutableMap<Int, String> = mutableMapOf(),
    internal val plurals: MutableMap<Int, Plural> = mutableMapOf()
)

fun registerSupportedLocales(vararg locales: Language) {
    locales.filter { it != Language.ENG }
        .forEach {
            registerLocalizationForLocale(it)
        }
}

private fun registerLocalizationForLocale(locale: Language) {
    localizationMap[locale] = Localization(locale)
}

fun Localization.getForRoute(route: String):String{
    if(route == "cards"){
        return LanguageSingleton
            .localization.value.cards()}
    else if(route == "finance"){
        return LanguageSingleton
            .localization.value.finance()}
    else if(route == "transfers"){
        return LanguageSingleton
            .localization.value.transfers()}
    else if(route == "history"){
        return LanguageSingleton
            .localization.value.history()}
    else{
        return LanguageSingleton
            .localization.value.profile()
    }
}

/**
 * Builder function for translatable string resource
 *
 * Saves given locales into corresponding [Localization] and returns extension function
 * that can be used for string resource retrieving
 *
 * @param defaultValue string value for default localization
 * @param localeToValue dictionary of locale to string resource
 * @param id integer id of a string resource
 * @return ext function that finds string in [Localization] receiver and returns it
 */
fun Translatable(
    defaultValue: String,
    localeToValue: Map<Language, String>,
    id: Int = generateUID()
): Localization.() -> String {
    defaultLocalization.strings[id] = defaultValue
    for ((locale, value) in localeToValue.entries) {
        val localization =
            localizationMap[locale] ?: throw RuntimeException("There is no locale $locale")
        localization.strings[id] = value
    }
    return fun Localization.(): String {
        return this.strings[id] ?: defaultLocalization.strings[id]
        ?: error("There is no string called $id in localization $this")
    }
}

/**
 * Builder function for translatable string resource
 *
 * Saves given locales into corresponding [Localization] and returns extension function
 * that can be used for string resource retrieving
 *
 * @param name id of a string resource, may be any object
 * @param defaultValue string value for default localization
 * @param localeToValue dictionary of locale to string resource
 * @return ext function that finds string in [Localization] receiver and returns it
 */
fun Translatable(
    name: Any,
    defaultValue: String,
    localeToValue: Map<Language, String>
): Localization.() -> String {
    return Translatable(
        defaultValue,
        localeToValue,
        generateUID(name)
    )
}

/**
 * Builder function for non-translatable string resource
 *
 * Saves given locales into corresponding [Localization] and returns extension function
 * that can be used for string resource retrieving
 *
 * @param defaultValue string value for default localization
 * @param id integer id of a string resource
 * @return ext function that finds string in [Localization] receiver and returns it
 */
fun NonTranslatable(defaultValue: String, id: Int = generateUID()): Localization.() -> String {
    defaultLocalization.strings[id] = defaultValue
    return fun Localization.(): String {
        return defaultLocalization.strings[id]
            ?: error("There is no string called $id in localization default")
    }
}

/**
 * Builder function for non-translatable string resource
 *
 * Saves given locales into corresponding [Localization] and returns extension function
 * that can be used for string resource retrieving
 *
 * @param name id of a string resource, may be any object
 * @param defaultValue string value for default localization
 * @return ext function that finds string in [Localization] receiver and returns it
 */
fun NonTranslatable(name: Any, defaultValue: String): Localization.() -> String {
    return NonTranslatable(defaultValue, generateUID(name))
}

val LocalLocalization = compositionLocalOf { defaultLocalization }

object Vocabulary {
    val localization: Localization
        @Composable
        @ReadOnlyComposable
        get() = LocalLocalization.current
}


fun localizationApp(locale: Language){
    LanguageSingleton.localization.value = localizationMap[locale]?: defaultLocalization
}

@Composable
fun Localization(locale: Language, content: @Composable () -> Unit) {
    println(locale)
    localizationMap[locale]?.let {
        println("GOT")
        CompositionLocalProvider(
            LocalLocalization provides it,
            content = content
        )
    }
}



