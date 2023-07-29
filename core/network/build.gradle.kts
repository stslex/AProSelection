import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("aproselection.android.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}

android.namespace = "com.stslex.aproselection.core.network"

val properties = gradleLocalProperties(rootDir)
val key = properties["API_KEY"]?.toString()
    ?: throw IllegalStateException("API_KEY")
val apiHost = properties["API_SERVER_HOST"]?.toString()
    ?: throw IllegalStateException("API_SERVER_HOST")
val apiVersion = properties["API_VERSION"]?.toString()
    ?: throw IllegalStateException("API_VERSION")

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", key)
        buildConfigField("String", "API_SERVER_HOST", apiHost)
        buildConfigField("String", "API_VERSION", apiVersion)
    }
}
