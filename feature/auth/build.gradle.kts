plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}

android.namespace = "com.stslex.aproselection.feature.auth"
