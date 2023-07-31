@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
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