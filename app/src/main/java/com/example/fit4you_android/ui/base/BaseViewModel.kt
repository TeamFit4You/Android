package com.example.fit4you_android.ui.base

import androidx.lifecycle.ViewModel
import com.example.fit4you_android.data.error.ErrorManager
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}
