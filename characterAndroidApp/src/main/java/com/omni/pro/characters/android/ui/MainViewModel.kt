package com.omni.pro.characters.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omni.pro.characters.android.events.MessageEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainViewModel : ViewModel() {
    init {
        EventBus.getDefault().register(this)
    }

    val messages: MutableStateFlow<String> = MutableStateFlow("")

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        when (event) {
            is MessageEvent.Error -> {
                viewModelScope.launch {
                    messages.emit(event.message)
                }
            }
        }
    }
}