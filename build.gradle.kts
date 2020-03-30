
buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(BuildPlugins.gradle)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinAppOpen)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean").configure {
    delete("build")
}