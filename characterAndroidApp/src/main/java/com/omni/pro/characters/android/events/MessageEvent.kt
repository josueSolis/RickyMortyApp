package com.omni.pro.characters.android.events

sealed class MessageEvent {
    data class Error(val message: String, val cause: Throwable) : MessageEvent()
}