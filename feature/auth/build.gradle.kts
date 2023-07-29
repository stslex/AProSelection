plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
}

android.namespace = "com.stslex.aproselection.feature.auth"
