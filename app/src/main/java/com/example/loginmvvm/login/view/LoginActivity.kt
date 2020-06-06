package com.example.loginmvvm.login.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.observe
import com.example.loginmvvm.R
import com.example.loginmvvm.databinding.LoginActivityBinding
import com.example.loginmvvm.login.base.BaseActivity
import com.example.loginmvvm.login.state.LoginScreenState
import com.example.loginmvvm.login.viewmodel.LoginActivityVM
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginActivityVM, LoginActivityBinding>(R.layout.activity_login) {

    // comment by srdpatel: 12/3/2019 KOIN has special direction to inject viewModel to support view life cycle
    override val viewModel: LoginActivityVM by viewModel()

    // comment by srdpatel: 12/3/2019 by inject is lazy di
    private val pref: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ((pref.getString("token", "") ?: "").isNotBlank()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // comment by srdpatel: 4/5/2020 LogInActivity observes the state defined in viewModel and based on changes, it updates ui.
        viewModel.state().observe(this) { state ->
            renderState(state)
        }

        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btnLogin.performClick()
                // comment by srdpatel: 4/5/2020 Inside lambda function, return keyword is not allowed.
                true
            } else false
        }
    }

    /**
     * 4/20/2020
     * A higher order function []
     * <p>
     * A function that has another function/s as parameter/s is known as a higher order function.
     * "doSomething" is a higher order function that has a function type (or say, lambda parameter) "(Int, Int) -> Int" as a last parameter.
     * It can also be said that “doSomething” has the last parameter as a lambda expression.
     * A functionType in any function parameter can be said as a lambda expression but
     * there is nothing such as functionType as an argument as it is always a lambda expression then.
     * </p>
     * @see <a href="http://google.com"></a>
     * [ReadableHyperlinkText]( "")
     * @author srdpatel
     * @since 1.0
     */
    fun doSomething(a: Int, b: Int, myFunction: (Int, Int) -> Int) {

    }

    /**
     * 4/13/2020
     * Single expression function returning what right side of the function returns which in our case, [Unit]
     * [Unit] is equivalent to void in Java
     * <p>
     * [run] is a scope function.
     * </p>
     *  {@link #} []
     *
     * @author srdpatel
     * @see <a href="http://google.com"></a>
     * [ReadableHyperlinkText]( "")
     * @since 1.0
     */
    private fun renderState(state: LoginScreenState) = state.run {
        when (state) {
            is LoginScreenState.LoginSuccess -> {
                // comment by srdpatel: 4/5/2020 such state will have LoginResponse DTO and LoginResponse DTO will have string property token.
                // We access get/set properties like this in kotlin.
                pref.edit().putString("token", state.response.token).apply()
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
            is LoginScreenState.LoginFailure -> {
                enableViews(true)
                Snackbar.make(root, state.error, Snackbar.LENGTH_SHORT).show()
            }
            is LoginScreenState.EmailValidationError -> {
                enableViews(true)
                tilEmail.error = state.error
            }
            is LoginScreenState.PasswordValidationError -> {
                enableViews(true)
                tilEmail.error = ""
                tilPassword.error = state.error
            }
            LoginScreenState.Loading -> {
                // comment by srdpatel: 4/5/2020 Reset TextInputLayout error
                tilEmail.error = ""
                tilPassword.error = ""
                enableViews(false)
            }
        }
    }

    private fun enableViews(enable: Boolean) {
        btnLogin.isEnabled = enable
        etEmail.isEnabled = enable
        etPassword.isEnabled = enable
        progressBar.visibility = if (enable) View.INVISIBLE else View.VISIBLE
    }
}
