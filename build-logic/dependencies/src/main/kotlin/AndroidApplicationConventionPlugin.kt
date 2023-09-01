import com.android.build.api.dsl.ApplicationExtension
import com.stslex.aproselection.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                namespace = "com.stslex.aproselection"

                defaultConfig.apply {
                    applicationId = "com.stslex.aproselection"
                    targetSdk = 33
                    versionName = "1.0"
                    versionCode = 1
                    buildTypes {
                        release {
                            isMinifyEnabled = false
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
            }
        }
    }
}
