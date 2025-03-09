plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser()
    }
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.okio)
                implementation(project.dependencies.platform(libs.redwood.bom))
                implementation(libs.redwood.treehouse)
                implementation(libs.redwood.treehouse.guest.compose)

                api(projects.schema.compose)
            }
        }
    }
}