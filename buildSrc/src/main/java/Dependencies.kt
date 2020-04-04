object App {
    const val id = "ir.alirezaiyan.arzte"
    const val name = "Arzte"
    const val compileSdk = 29
    const val buildTools = "29.0.2"
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "3.5.3"
    const val kotlin = "1.3.30"
    const val assertK = "0.13"
    const val timber = "4.7.1"
    const val coroutines = "1.2.0"

    const val appcompat = "1.1.0"
    const val material = "1.1.0-alpha10"
    const val recyclerview = "1.0.0"
    const val navigation = "1.0.0"
    const val dagger = "2.22.1"
    const val gson = "2.8.5"
    const val picasso = "2.71828"
    const val okHttp = "3.12.0"
    const val okHttpInterceptor = "3.8.1"
    const val retrofit = "2.6.0"
    const val retrofitGson = "2.6.0"
    const val support = "1.0.0"

    const val lifecycle = "2.2.0-alpha04"

    const val ktx = "1.0.1"

    const val junit = "4.12"
    const val testExt = "1.1.1"
    const val fragmentTest = "1.2.2"
    const val mockito = "2.23.0"
    const val espresso = "3.2.0"
    const val assertjCore = "3.11.1"
    const val mockitoKotlin = "2.1.0"
    const val mockitoInline = "2.8.9"
    const val robolectric = "3.8"
    const val kluent = "1.14"

}


object BuildPlugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinAppOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
}

object AppPlugins {
    const val androidApp = "com.android.application"
    const val androidLib = "com.android.library"
    const val kotlin = "kotlin-android"
    const val kotlinExt = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAllOpen = "kotlin-allopen"
    const val kotlinAllOpenDir = "ir.alirezaiyan.arzte.core.AllOpen"
}

object AppModule {
    const val app = ":app"
    const val core = ":core"
    const val testSdk = ":test-sdk"
    const val uiSdk = ":ui-sdk"
    const val androidTestSdk = ":androidtest-sdk"
    const val uiPrimaryList = ":ui-primary-list"
}

object Deps {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val navigationUI = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okhttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpInterceptor}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"

    const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.support}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val assertK = "com.willowtreeapps.assertk:assertk-jvm:${Versions.assertK}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}


object TestLibraries {
    const val androidJUnitRunner = "ir.alirezaiyan.arzte.androidtest_sdk.MockTestRunner"
    const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
    const val junit = "junit:junit:${Versions.junit}"
    const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
    const val runner = "androidx.test:runner:1.1.0"
    const val rules = "androidx.test:rules:1.2.0"
    const val coreTesting = "androidx.arch.core:core-testing:2.0.1"
    const val core = "androidx.test:core:1.2.0"
    const val daggerMock = "com.github.fabioCollini.daggermock:daggermock:0.8.4"
    const val daggerMockKotlin = "com.github.fabioCollini.daggermock:daggermock-kotlin:0.8.4"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val xjunit = "androidx.test.ext:junit:1.1.1"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    const val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

}


