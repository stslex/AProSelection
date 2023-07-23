plugins {
    id("aproselection.android.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.okhttp)
}

android.namespace = "com.stslex.aproselection.core.network"
