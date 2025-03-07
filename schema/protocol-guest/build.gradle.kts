plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("app.cash.redwood.generator.protocol.guest")
}
base.archivesName = "schema-protocol-guest"

kotlin {
    js {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.schema.widget)
            }
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.Schema"
}
