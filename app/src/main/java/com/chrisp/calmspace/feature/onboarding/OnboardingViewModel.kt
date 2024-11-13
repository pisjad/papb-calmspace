package com.chrisp.calmspace.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.chrisp.calmspace.R
import com.chrisp.calmspace.model.OnboardingModel

class OnboardingViewModel : ViewModel() {
    private val _currentIndex = mutableStateOf(0)
    val currentIndex: State<Int> = _currentIndex

    private val _onboardingItems = listOf(
        OnboardingModel(R.drawable.mental_health_v1, "Selamat Datang di CalmSpace", "Temukan langkah-langkah praktis dan dukungan yang Anda butuhkan untuk menjaga kesehatan mental setiap hari. Mulai perjalanan Anda menuju kehidupan yang lebih seimbang, tenang, dan bahagia."),
        OnboardingModel(R.drawable.mental_health_v2, "Konsultasi Mudah dan Cepat ", "Dapatkan dukungan dan solusi dari para ahli melalui konsultasi mudah, kapan saja dan di mana saja. Bersama kami, setiap langkah menuju kesehatan mental yang lebih baik jadi lebih ringan"),
        OnboardingModel(R.drawable.mental_health_v3, "Berbagi Cerita, Saling Mendukung", "Temukan kekuatan dalam komunitas melalui forum diskusi. Di sini, Anda tidak pernah sendiri—bersama kita bisa tumbuh dan sembuh")
    )
    val onboardingItems: List<OnboardingModel> = _onboardingItems

    fun nextScreen() {
        if (_currentIndex.value < _onboardingItems.size - 1) {
            _currentIndex.value += 1
        }
    }

    fun previousScreen() {
        if (_currentIndex.value > 0) {
            _currentIndex.value -= 1
        }
    }
}
