rootProject.name = "kmp-redwood-example"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")
include(":shared")
include(":shared-compose")
include(":presenter")
include(":schema")
include(":schema:compose")
include(":schema:widget")
//Treehouse
include(":launcher")
include(":values")
include(":presenter-treehouse")
include(":presenter-remote")
include(":schema:modifiers")
include(":schema:protocol-guest")
include(":schema:protocol-host")
