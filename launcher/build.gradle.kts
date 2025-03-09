plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("app.cash.zipline")
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    androidTarget()

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.redwood.bom))
            implementation(libs.redwood.treehouse.host)
            api(projects.presenterTreehouse)
            api(libs.zipline.loader)
        }
    }
}

android {
    namespace = "com.santimattius.kmp.redwood.launcher"
}