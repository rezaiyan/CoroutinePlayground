plugins {
    id(AppPlugins.androidLib)
    id(AppPlugins.kotlin)
}
android {

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
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

dependencies {
    implementation(project(AppModule.core))
    implementation(Deps.kotlin)
    api(Deps.assertK)
    api(TestLibraries.junit)
    api(TestLibraries.mockitoKotlin)
    api(TestLibraries.coreTesting){
        exclude("com.android.support","support-annotations")
        exclude("com.android.support","support-compat")
        exclude("com.android.support","support-core-utils")
    }
}
