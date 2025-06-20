package com.example.sandboxbank.profile.data

sealed class ProfileState {
    data class CurrentSets(val isDark: Boolean, val language: com.example.sandboxbank.profile.data.Language): ProfileState()
    data class Theme(val isDark: Boolean): ProfileState()
    data class Language(val isEng: Boolean): ProfileState()
}