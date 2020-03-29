plugins {
    id(AppPlugins.androidLib)
    id(AppPlugins.kotlin)
    id(AppPlugins.kotlinExt)
    id(AppPlugins.kotlinKapt)
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
    implementation(project(AppModule.core))
    implementation(Deps.kotlin)
    implementation(Deps.picasso)
    api(Deps.ktx)
    api(Deps.material)
    api(Deps.navigationUI)
    api(Deps.navigationFragment)
    api(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleExtension)
    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
}
