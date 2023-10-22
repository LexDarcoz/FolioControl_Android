package foliocontrol.android.foliocontrolandroid.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.context.User
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    fun performLogin(email: String, password: String, userState: MutableState<User>) {

        viewModelScope.launch {

        }
    }
}