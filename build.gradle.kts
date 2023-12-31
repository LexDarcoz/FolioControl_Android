// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.dokka") version "1.9.10"

}

buildscript {
    dependencies {

        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.9.10")
    }
    extra.apply {
        set("room_version", "2.5.2")
    }
}
