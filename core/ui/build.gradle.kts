plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
}

android.namespace = "com.stslex.aproselection.core.ui"

dependencies {
    implementation(project(":core:core"))
}
