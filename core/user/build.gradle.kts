plugins {
    id("aproselection.android.library")
}

android.namespace = "com.stslex.aproselection.core.user"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:network"))
    testImplementation(project(":core:core"))
    androidTestImplementation(project(":core:core"))
}