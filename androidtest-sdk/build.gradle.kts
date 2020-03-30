plugins {
    id(AppPlugins.androidLib)
    id(AppPlugins.kotlinKapt)
    id(AppPlugins.kotlin)
    id(AppPlugins.kotlinExt)
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
    api(project(AppModule.uiPrimaryList))
    api(project(AppModule.core))
    api(Deps.kotlin)
    api(Deps.androidLegacy)
    api(TestLibraries.rules)
    api(TestLibraries.junit)
    api(TestLibraries.core)
    api(TestLibraries.coreTesting)
    api(TestLibraries.mockitoAndroid)
    api(TestLibraries.daggerMock)
    api(TestLibraries.daggerMockKotlin) {
        exclude("org.jetbrains.kotlin")
    }
    api(TestLibraries.espressoCore) {
        exclude("com.android.support","support-annotations")
        exclude("com.google.code.findbugs","jsr305")
    }
    api(TestLibraries.espressoContrib) {
        exclude("com.android.support","support-annotations")
        exclude("com.google.code.findbugs","jsr305")
    }
//    implementation(Deps.dagger)
//    kapt(Deps.daggerCompiler)
}
