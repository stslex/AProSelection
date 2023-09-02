pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "AProSelection"

include(":app")

include(":core:network")
include(":feature:auth")
include(":core:ui")
include(":core:datastore")
include(":feature:home")
include(":core:user")
include(":core:core")
include(":core:navigation")
include(":feature:main")
