package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.plaintext.data.dao.PasswordDao
import com.example.plaintext.data.model.Password
import com.example.plaintext.data.repository.LocalPasswordDBStore
import com.example.plaintext.data.repository.PasswordDBStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListViewState(
    var passwordList: List<Password>,
    var isCollected: Boolean = false
)

//Utilize o passwordBDStore para obter a lista de senhas e salva-las
@HiltViewModel
open class ListViewModel @Inject constructor (
    passwordDao: PasswordDao
) : ViewModel() {
    var listViewState by mutableStateOf(ListViewState(passwordList = emptyList()))
        private set

//    val passwordDBStore: LocalPasswordDBStore = LocalPasswordDBStore()
    private val passwordDBStore = LocalPasswordDBStore(passwordDao)

    init {
        viewModelScope.launch {
            passwordDBStore.getList().collect {
                listViewState = listViewState.copy(
                    passwordList = it,
                    isCollected = true
                )
            }
        }
    }


    fun savePassword(password: Password){
        viewModelScope.launch {
            passwordDBStore.save(password)
        }
    }
}
