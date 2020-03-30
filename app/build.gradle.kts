plugins {
    id(AppPlugins.androidApp)
    id(AppPlugins.kotlin)
    id(AppPlugins.kotlinKapt)
    id(AppPlugins.kotlinExt)
}

androidExtensions { isExperimental = true }

android {

    compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
    compileOptions.targetCompatibility = JavaVersion.VERSION_1_8
    compileSdkVersion(App.compileSdk)
    buildToolsVersion(App.buildTools)

    defaultConfig {
        testInstrumentationRunner = TestLibraries.androidJUnitRunner
        applicationId = App.id
        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)
        versionName = App.versionName
        versionCode = App.versionCode
        vectorDrawables.useSupportLibrary = true
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
    implementation(project(AppModule.core))
    implementation(project(AppModule.uiPrimaryList))
    implementation(Deps.kotlin)
    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
    implementation(Deps.timber)
    implementation(Deps.appcompat)
    implementation(Deps.recyclerview)
}
