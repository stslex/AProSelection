plugins {
    id("aproselection.android.application")
    id("aproselection.android.application.compose")
}

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":core:user"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:main"))
}