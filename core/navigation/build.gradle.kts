plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
}

android.namespace = "com.stslex.aproselection.core.navigation"

dependencies {
    implementation(project(":core:core"))
}