plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.redwood.generator.protocol.host")
}
base.archivesName = "schema-protocol-host"

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(project.dependencies.platform(libs.redwood.bom))
            api(libs.redwood.layout.widget)

            api(projects.schema.widget)
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.DragonBall"
}
