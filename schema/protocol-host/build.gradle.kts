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
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.redwood.bom))
                implementation(libs.redwood.layout.widget)
                implementation(projects.schema.widget)
            }
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.DragonBall"
}
