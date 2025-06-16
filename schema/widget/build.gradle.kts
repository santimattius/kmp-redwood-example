plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.redwood.generator.widget")
}

base.archivesName = "schema-widget"

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
            api(project.dependencies.platform(libs.redwood.bom))
            api(libs.redwood.layout.widget)
            api(libs.redwood.lazylayout.widget)

            api(projects.schema.modifiers)
            api(projects.values)
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.DragonBall"
}