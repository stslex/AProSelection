plugins {
    id("aproselection.android.application")
    id("aproselection.android.application.compose")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
}