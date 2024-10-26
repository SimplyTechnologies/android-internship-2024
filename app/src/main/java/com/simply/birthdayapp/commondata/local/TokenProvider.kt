package com.simply.birthdayapp.commondata.local

import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TokenProvider(private val dataStoreProvider: DataStoreProvider) {
    private val _tokenFlow = MutableStateFlow<String?>(null)
    val tokenFlow: StateFlow<String?> = _tokenFlow.asStateFlow()

    init {
        // Launch a coroutine to fetch and keep the token updated
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreProvider.getToken().collect { token ->
                _tokenFlow.value = token
            }
        }
    }
}
