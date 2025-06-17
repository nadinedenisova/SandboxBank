package com.example.sandboxbank.profile.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sandboxbank.profile.data.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.Boolean

class ProfileScreenViewModel @Inject constructor(
    settingStoreManager: SettingStoreManager
) : ViewModel() {

    private var _profileState = MutableStateFlow<ProfileState>(ProfileState.CurrentSets(settingStoreManager.getTheme(), settingStoreManager.getLanguage()))
    val profileState: StateFlow<ProfileState> = _profileState


    fun create(){
        println("Hi")
    }
}