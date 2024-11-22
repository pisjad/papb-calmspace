package com.chrisp.calmspace.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisp.calmspace.model.ProfileModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profileState = MutableStateFlow(
        ProfileModel(
            name = "Qyan Rommy Mario",
            email = "qyanmario@gmail.com",
            profilePictureUrl = "https://unsplash.com/photos/a-green-and-purple-aurora-bore-over-a-body-of-water-U3YmD3Ea4vE"
        )
    )
    val profileState: StateFlow<ProfileModel> get() = _profileState

    fun updateProfilePicture(newUrl: String) {
        viewModelScope.launch {
            _profileState.value = _profileState.value.copy(profilePictureUrl = newUrl)
        }
    }
}
