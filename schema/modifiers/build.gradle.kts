plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.redwood.generator.modifiers")
}
base.archivesName = "schema-modifiers"

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    js {
        browser()
    }

    jvm()
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.Schema"
}
