/*
 * Created by Birju Vachhani on 20 September 2019
 * Copyright © 2019 Gradle Utils. All rights reserved.
 */

/*
 * Contains all the core dependencies
 */
object Core {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:1.3.50"
}

object AndroidX {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val appCompat = "androidx.appcompat:appcompat:1.1.0"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
    const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:1.0.0"
    const val viewPager = "androidx.viewpager:viewpager:1.0.0"
    const val viewPager2 = ""
    const val cardView = "androidx.cardview:cardview:1.0.0"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:1.1.0"
    const val fragment = "androidx.fragment:fragment:1.1.0"
    const val emoji = "androidx.emoji:emoji:1.0.0"
    const val annotation = "androidx.annotation:annotation:1.1.0"
    const val print = "androidx.print:print:1.0.0"
    const val preference = "androidx.preference:preference:1.1.0"
    const val palette = "androidx.palette:palette:1.0.0"
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val loader = "androidx.loader:loader:1.1.0"
    const val leanback = "androidx.leanback:leanback:1.0.0"
    const val gridLayout = "androidx.gridlayout:gridlayout:1.0.0"
    const val exifInterface = "androidx.exifinterface:exifinterface:1.0.0"
    const val drawerLayout = "androidx.drawerlayout:drawerlayout:1.0.0"
    const val materialDesign = "com.google.android.material:material:1.0.0"
    const val browser = "androidx.browser:browser:1.0.0"
    const val archTest = "androidx.arch.core:core-testing:2.1.0"

}

object JUnit {
    const val core = "junit:junit:4.12"
    const val extensions = "androidx.test.ext:junit:1.1.1"
}

object Espresso {
    @JvmStatic
    var version = "3.2.0"
    @JvmStatic
    val core: String
        get() = "androidx.test.espresso:espresso-core:$version"
    @JvmStatic
    val intents: String
        get() = "androidx.test.espresso:espresso-intents:$version"
    @JvmStatic
    val contrib: String
        get() = "androidx.test.espresso:espresso-contrib:$version"
    @JvmStatic
    val accessibility: String
        get() = "androidx.test.espresso:espresso-accessibility:$version"
    @JvmStatic
    val remote: String
        get() = "androidx.test.espresso:espresso-remote:$version"
    @JvmStatic
    val web: String
        get() = "androidx.test.espresso:espresso-web:$version"
}

object Room {
    @JvmStatic
    var version = "2.2.1"
    @JvmStatic
    val common: String
        get() = "androidx.room:room-common:$version"
    @JvmStatic
    val runtime: String
        get() = "androidx.room:room-runtime:$version"
    @JvmStatic
    val compiler: String
        get() = "androidx.room:room-compiler:$version"
    @JvmStatic
    val migration: String
        get() = "androidx.room:room-migration:$version"
    @JvmStatic
    val rxjava2: String
        get() = "androidx.room:room-rxjava2:$version"
}

object Paging {
    @JvmStatic
    var version = "2.1.0"
    @JvmStatic
    val common: String
        get() = "androidx.paging:paging-common:$version"
    @JvmStatic
    val runtime: String
        get() = "androidx.paging:paging-runtime:$version"
    @JvmStatic
    val rxjava2: String
        get() = "androidx.paging:paging-rxjava2:$version"
}

object Lifecycle {
    @JvmStatic
    var version = "2.1.0"
    @JvmStatic
    val common: String
        get() = "androidx.lifecycle:lifecycle-common:$version"
    @JvmStatic
    val commonJava8: String
        get() = "androidx.lifecycle:lifecycle-common-java8:$version"
    @JvmStatic
    val compiler: String
        get() = "androidx.lifecycle:lifecycle-compiler:$version"
    @JvmStatic
    val extensions: String
        get() = "androidx.lifecycle:lifecycle-extensions:$version"
    @JvmStatic
    val livedata: String
        get() = "androidx.lifecycle:lifecycle-livedata:$version"
    @JvmStatic
    val livedataCore: String
        get() = "androidx.lifecycle:lifecycle-livedata-core:$version"
    @JvmStatic
    val reactiveStreams: String
        get() = "androidx.lifecycle:lifecycle-reactivestreams:$version"
    @JvmStatic
    val runtime: String
        get() = "androidx.lifecycle:lifecycle-runtime:$version"
    @JvmStatic
    val viewmodel: String
        get() = "androidx.lifecycle:lifecycle-viewmodel:$version"
}

object OkHttp {
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.2.1"
}

object Retrofit {
    var version = "2.6.0"
    @JvmStatic
    val core
        get() = "com.squareup.retrofit2:retrofit:$version"
    @JvmStatic
    val gsonConverter
        get() = "com.squareup.retrofit2:converter-gson:$version"
}

object Koin {
    var version = "2.0.1"
    @JvmStatic
    val core = "org.koin:koin-android:$version"
    @JvmStatic
    val scope = "org.koin:koin-android-scope:$version"
    @JvmStatic
    val viewModel
        get() = "org.koin:koin-android-viewmodel:$version"
    @JvmStatic
    val test
        get() = "org.koin:koin-test:$version"
}

object Mockito {
    const val core = "org.mockito:mockito-core:2.28.2"
}

object KTX {
    const val core = "androidx.core:core-ktx:1.0.1"
    const val palette = "androidx.palette:palette-ktx:1.0.0"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.1.0"
    const val activity = "androidx.activity:activity-ktx:1.1.0-rc02"
    const val collection = "androidx.collection:collection-ktx:1.1.0"
    const val sqlite = "androidx.sqlite:sqlite-ktx:2.0.1"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
    const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.1.0"
    const val workManager = "androidx.work:work-runtime-ktx:2.2.0"

    object Navigation {
        var version = "2.1.0"
        @JvmStatic
        val runtime: String
            get() = "androidx.navigation:navigation-runtime-ktx:$version"
        @JvmStatic
        val fragment: String
            get() = "androidx.navigation:navigation-fragment-ktx:$version"
        @JvmStatic
        val ui: String
            get() = "androidx.navigation:navigation-ui-ktx:$version"
    }
}

object Coroutines {
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.2"
}

object AndroidTest {
    const val runner = "androidx.test:runner:1.2.0"
    const val rule = "androidx.test:rules:1.2.0"
    const val room = "androidx.room:room-testing:1.0.0"
}

infix fun String.version(version: String) = replaceAfterLast(":", version)

object With {

    @JvmStatic
    fun version(dep: String, version: String) = dep.replaceAfterLast(":", version)
}