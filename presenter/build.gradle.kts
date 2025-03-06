plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
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
            api(projects.schema.compose)
            implementation(project.dependencies.platform(libs.redwood.bom))
            api(libs.redwood.layout.compose)
        }
    }
}