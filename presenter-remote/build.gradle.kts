plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerializationPlugin)
}

kotlin {
    js {
        browser()
    }
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.okio)
            implementation(project.dependencies.platform(libs.redwood.bom))
            implementation(libs.redwood.treehouse)
            implementation(libs.redwood.treehouse.guest.compose)
            implementation(libs.kotlinx.serialization.core)
            api(projects.schema.compose)
        }
    }
}