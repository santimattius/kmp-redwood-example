plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.redwood.generator.compose")
}

base.archivesName = "schema-compose"

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
            implementation(project.dependencies.platform(libs.redwood.bom))
            api(libs.redwood.layout.compose)
            implementation(projects.schema.widget)
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.Schema"
}
