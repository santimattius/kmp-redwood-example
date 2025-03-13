plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.zipline")
}
//TODO: review
//redwoodBuild {
//    ziplineApplication('emoji-search')
//}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    js {
        // The name of the JS module which needs to be unique within the repo.
        moduleName = "emoji-search-presenter-treehouse"
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.okio)
                implementation(project.dependencies.platform(libs.redwood.bom))
                implementation(libs.redwood.treehouse)
            }
        }

        jsMain {
            dependencies {
                implementation(project.dependencies.platform(libs.redwood.bom))
                implementation(libs.redwood.treehouse.guest)

                api(projects.presenterRemote)
                api(projects.schema.protocolGuest)
            }
        }
    }
}

zipline {
    mainFunction = "com.santimattius.kmp.redwood.treehouse.preparePresenters"
}