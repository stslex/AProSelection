plugins {
    `kotlin-dsl`
}

group = "com.stslex.aproselection.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serialization)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "aproselection.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "aproselection.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "aproselection.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "aproselection.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}