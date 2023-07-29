plugins {
    id("aproselection.android.library")
}

android.namespace = "com.stslex.aproselection.core.datastore"

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore)
}