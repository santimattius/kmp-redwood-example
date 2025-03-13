plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerializationPlugin)
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    js {
        browser()
    }
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.kotlinx.serialization.core)
            implementation(project.dependencies.platform(libs.redwood.bom))
            implementation(libs.redwood.treehouse)
            implementation(libs.redwood.widget)
        }
    }
}