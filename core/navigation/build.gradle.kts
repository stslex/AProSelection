plugins {
    id("aproselection.android.library")
}

android.namespace = "com.stslex.aproselection.core.navigation"

dependencies {
    implementation(project(":core:core"))
}