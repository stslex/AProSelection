plugins {
    id("aproselection.android.library")
    id("aproselection.android.library.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:user"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
}

android.namespace = "com.stslex.aproselection.feature.home"
