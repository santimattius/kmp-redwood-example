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
        commonMain.dependencies {
            api(project.dependencies.platform(libs.redwood.bom))
            api(libs.redwood.layout.widget)
            api(libs.redwood.lazylayout.widget)
            api(projects.schema.widget)
        }
    }
}

redwoodSchema {
    source = projects.schema
    type = "com.santimattius.kmp.redwood.example.DragonBall"
}
