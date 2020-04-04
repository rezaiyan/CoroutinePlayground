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

    dataBinding.isEnabled = true
}

dependencies {
    api(project(AppModule.uiSdk))
    implementation(project(AppModule.core))
    implementation(Deps.kotlin)
    implementation(Deps.dagger)
    testImplementation(project(AppModule.testSdk))
    kapt(Deps.daggerCompiler)
}
