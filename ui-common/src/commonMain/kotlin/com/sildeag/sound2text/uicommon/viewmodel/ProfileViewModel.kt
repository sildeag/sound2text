package com.sildeag.sound2text.uicommon.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(
    private val repository: ProfileRepository
) {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state
    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.Refresh -> loadProfile()
            is ProfileEvent.NameChanged ->
                _state.update { it.copy(header = it.header.copy(name
                = event.value)) }
            is ProfileEvent.AvatarClicked ->
                handleAvatarClick(event.id)
        }
    }
    private fun loadProfile() {
        _state.update { it.copy(isLoading = true) }
        // suspend logic here
    }
    private fun handleAvatarClick(id: String) {
        // logic here
    }
}

annotation class ProfileState

annotation class ProfileRepository

class ProfileEvent {

}
