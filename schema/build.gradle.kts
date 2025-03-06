plugins {
    alias(libs.plugins.kotlinJvm)
    id("app.cash.redwood.schema")
}

dependencies {
    implementation(project.dependencies.platform(libs.redwood.bom))
    api(libs.redwood.layout.schema)
}

redwoodSchema {
    type = "com.santimattius.kmp.redwood.example.Schema"
}