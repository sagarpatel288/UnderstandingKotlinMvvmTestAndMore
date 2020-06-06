package com.example.loginmvvm.login.di

import android.content.Context
import android.util.Patterns
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.util.regex.Pattern

/*
 * Created by Birju Vachhani on 18 November 2019
 * Copyright © 2019 Login MVVM. All rights reserved.
 */

val coreModule = module {

    single {
        androidApplication().getSharedPreferences("app_pref", Context.MODE_PRIVATE)
    }

    // comment by srdpatel: 4/11/2020 Because many tests don't know about this android framework, we can mock it
    single<Pattern> {
        Patterns.EMAIL_ADDRESS
    }
}