package com.example.common.enums



sealed class UIEvent {
    data class ShowSnackbar(val message: String) : UIEvent()
    data class Navigate(val route: String) : UIEvent()

    data class ShowDialog(val dialogName : String) : UIEvent()
    data class SessionKill(val message : String)  : UIEvent()
}