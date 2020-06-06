package com.example.loginmvvm.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.DefaultDispatcherProvider
import com.example.loginmvvm.DispatcherProvider
import com.example.loginmvvm.login.base.BaseVM
import com.example.loginmvvm.login.model.remote.ApiService
import com.example.loginmvvm.login.model.remote.error.LoginError
import com.example.loginmvvm.login.model.remote.request.LoginRequest
import com.example.loginmvvm.login.state.LoginScreenState
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject
import retrofit2.HttpException
import java.util.regex.Pattern

/*
 * Created by Birju Vachhani on 15 November 2019
 * Copyright © 2019 Login MVVM. All rights reserved.
 */

class LoginActivityVM(private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()) :
    BaseVM() {

    // comment by srdpatel: 4/11/2020 Because many tests don't know about this android framework, we can mock it
    private val emailPattern: Pattern by inject()

    private val apiService: ApiService by inject()

    // comment by srdpatel: 4/6/2020 These 2 live data are part of two way data binding
    val emailLiveData = MutableLiveData<String>("")
    val passwordLiveData = MutableLiveData<String>("")

    /**
     * 4/11/2020
     * comment by srdpatel: 4/6/2020 This is the only mutable live data that is being observed by view
     * <p>
     * Best practices for mvvm says that we should not expose mutable live data to view.
     * To understand why private val and getting it through [state], check the link.
     * </p>
     *  {@link #} []
     *
     * @author srdpatel
     * @see <a href="comment by srdpatel: 4/6/2020 This is the only mutable live data that is being observed by view">Best practices for mvvm</a>
     * [Do not expose mutable data to view](https://proandroiddev.com/5-common-mistakes-when-using-architecture-components-403e9899f4cb "Best practices for MVVM")
     * @since 1.0
     */
    private val state = MutableLiveData<LoginScreenState>(LoginScreenState.Initial)

    /**
     * 4/11/2020
     * comment by srdpatel: 12/3/2019 static method that returns static final variable [state]
     * <p>
     * Best practices for mvvm says that we should not expose mutable live data to view.
     * To understand why private val and getting it through [state], check the link.
     * </p>
     *  {@link #} []
     *
     * @author srdpatel
     * @see <a href="comment by srdpatel: 4/6/2020 This is the only mutable live data that is being observed by view">Best practices for mvvm</a>
     * [Do not expose mutable data to view](https://proandroiddev.com/5-common-mistakes-when-using-architecture-components-403e9899f4cb "Best practices for MVVM")
     * @since 1.0
     */
    fun state(): LiveData<LoginScreenState> = state

    /**
     * 4/7/2020
     * This function has been bounded in xml with login button [.main.res.activity_login.xml]
     * <p>
     * This is single expression function, expression body.
     * Any coroutine launched with this scope (viewModelScope) is automatically canceled if the ViewModel is cleared.
     * [Better coding] (https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b "Proper Dispatcher")
     * Because we are using viewModel ktx, we don't need to override onCleared.
     * The [userLogIn] function has been trigger from this function which uses [Dispatchers.Default] and
     * we know that network request should be happened in [Dispatchers.IO]. So, we have changed the
     * dispatcher in [userLogIn] method using [withContext].
     * </p>
     *  {@link #} [.main.res.activity_login.xml]
     *
     * @param
     * @return
     * @author srdpatel
     * @see <a href="https://developer.android.com/topic/libraries/architecture/coroutines">More on coroutines</a>
     * @see <a href="https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b">Better Coding</a>
     * [More on Coroutines] (https://developer.android.com/topic/libraries/architecture/coroutines "More on coroutines")
     * [Better coding] (https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b "Proper Dispatcher")
     * @since 1.0
     */
    fun login(email: String, password: String) = viewModelScope.launch(dispatchers.default()) {
        if (email.isBlank()) {
            state.postValue(LoginScreenState.EmailValidationError("Email cannot be empty"))
            return@launch
        } else if (!emailPattern.matcher(email).matches()) {
            state.postValue(LoginScreenState.EmailValidationError("Invalid email address"))
            return@launch
        }

        if (password.isBlank()) {
            state.postValue(LoginScreenState.PasswordValidationError("Password cannot be empty"))
            return@launch
        } else if (password.length !in 8..20) {
            state.postValue(LoginScreenState.PasswordValidationError("Password must be 8 to 20 characters long!"))
            return@launch
        }
        state.postValue(LoginScreenState.Loading)
        userLogIn(email, password)
        /* // comment by srdpatel: 4/6/2020 runCatching is a part of kotlin standard library to handle anything that can either success or fail
         // comment by srdpatel: 4/6/2020 https://medium.com/@jcamilorada/arrow-try-is-dead-long-live-kotlin-result-5b086892a71e
         runCatching {
             apiService.login(LoginRequest(email, password))
             // comment by srdpatel: 4/6/2020 fold is a part of kotlin standard library to handle result
         }.fold(
             // comment by srdpatel: 4/6/2020 success response
             {
                 state.postValue(LoginScreenState.LoginSuccess(it))
             },
             // comment by srdpatel: 4/6/2020 failure
             {
                 val error = when (it) {
                     is HttpException -> {
                         it.response()?.errorBody()?.string()?.let { error ->
                             Gson().fromJson(error, LoginError::class.java).error
                         } ?: "something went wrong"
                     }
                     else -> "Something went wrong!"
                 }
                 state.postValue(LoginScreenState.LoginFailure(error))
             })*/
    }

    /**
     * 4/7/2020
     * Network request should be executed in Dispatcher.IO (background) thread
     * <p>
     * But this function is inside Dispatcher.Default [com.example.loginmvvm.login.viewmodel.LoginActivityVM.login].
     * So, we had to change the thread using [withContext]
     * </p>
     * @see <a href="https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b">Coroutines</a>
     * [Coroutines](https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b "Coroutines")
     * @author srdpatel
     * @since 1.0
     */
    private suspend fun userLogIn(email: String, password: String) {
        // comment by srdpatel: 4/7/2020 Network request should be executed in Dispatcher.IO (background) thread
        // comment by srdpatel: 4/7/2020 But this function is inside Dispatcher.Default, so we had to change the thread using withContext
        withContext(Dispatchers.IO) {
            // comment by srdpatel: 4/6/2020 runCatching is a part of kotlin standard library to handle anything that can either success or fail
            // comment by srdpatel: 4/6/2020 https://medium.com/@jcamilorada/arrow-try-is-dead-long-live-kotlin-result-5b086892a71e
            runCatching {
                apiService.login(LoginRequest(email, password))
                // comment by srdpatel: 4/6/2020 fold is a part of kotlin standard library to handle result
            }.fold(
                // comment by srdpatel: 4/6/2020 success response
                {
                    state.postValue(LoginScreenState.LoginSuccess(it))
                },
                // comment by srdpatel: 4/6/2020 failure
                {
                    val error = when (it) {
                        is HttpException -> {
                            it.response()?.errorBody()?.string()?.let { error ->
                                Gson().fromJson(error, LoginError::class.java).error
                            } ?: "something went wrong"
                        }
                        else -> "Something went wrong!"
                    }
                    state.postValue(LoginScreenState.LoginFailure(error))
                })
        }
    }
}