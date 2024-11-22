package com.chrisp.calmspace.feature.konsultasi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KonsultasiViewModel : ViewModel() {
    private val _state = MutableStateFlow(KonsultasiState())
    val state: StateFlow<KonsultasiState> = _state

    fun updateSearchQuery(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
    }

    fun updateSelectedTab(index: Int) {
        _state.value = _state.value.copy(selectedTab = index)
    }
}

data class KonsultasiState(
    val searchQuery: String = "",
    val selectedTab: Int = 0,
    val tabs: List<String> = listOf("Aktif", "Terjadwal", "Riwayat")
)
