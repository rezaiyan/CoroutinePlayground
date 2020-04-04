plugins {
    id(AppPlugins.androidLib)
    id(AppPlugins.kotlin)
    id(AppPlugins.kotlinExt)
    id(AppPlugins.kotlinKapt)
    id(AppPlugins.kotlinAllOpen)
}
allOpen {
    annotation(AppPlugins.kotlinAllOpenDir)
}
android {

    compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
    compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
    compileSdkVersion(App.compileSdk)
    buildToolsVersion(App.buildTools)

    defaultConfig {
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionName = App.versionName
        versionCode = App.versionCode
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}
configurations{
    compile.configure { exclude("org.jetbrains.kotlinx","atomicfu-common") }
}
dependencies {
    implementation(Deps.kotlin)
    api(Deps.coroutines)
    api(Deps.coroutinesAndroid)
    implementation(Deps.dagger)
    implementation(Deps.gson)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.okhttpInterceptor)
    kapt(Deps.daggerCompiler)
    api(Deps.lifecycleViewModel){
        exclude("org.jetbrains.kotlinx","kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx","kotlinx-coroutines-android")
    }
    api(Deps.lifecycleExtension){
        exclude("org.jetbrains.kotlinx","kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx","kotlinx-coroutines-android")
    }
    testImplementation(project(AppModule.testSdk))
}
