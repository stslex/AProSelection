plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
}

android.namespace = "com.stslex.aproselection.feature.main"