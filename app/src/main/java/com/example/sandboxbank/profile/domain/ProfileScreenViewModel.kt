package com.example.sandboxbank.profile.domain

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor() : ViewModel() {

    fun create(){
        println("Hi")
    }
}