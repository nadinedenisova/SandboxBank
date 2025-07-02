package com.example.sandboxbank.profile.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandboxbank.profile.data.ProfileState
import com.example.sandboxbank.profile.ui.ProfileEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Boolean

class ProfileScreenViewModel @Inject constructor(
    private val settingStoreManager: SettingStoreManager
) : ViewModel() {

    private var _profileState = MutableStateFlow<ProfileState>(ProfileState.CurrentSets(settingStoreManager.getTheme(), settingStoreManager.getLanguage()))
    val profileState: StateFlow<ProfileState> = _profileState


    fun create(){
        println("Hi")
    }

    fun onEvent(event: ProfileEvent){
        when(event){
            is ProfileEvent.SetLanguage -> viewModelScope.launch {
                settingStoreManager.setLanguage(event.lang)
                _profileState.value = ProfileState.Language(event.lang)
            }
            is ProfileEvent.SetTheme -> viewModelScope.launch {
                settingStoreManager.setDarkTheme(event.theme)
                _profileState.value = ProfileState.Theme(event.theme)
            }
        }
    }
}