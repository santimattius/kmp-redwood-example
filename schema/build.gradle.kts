plugins {
    alias(libs.plugins.kotlinJvm)
    id("app.cash.redwood.schema")
}

dependencies {
    implementation(project.dependencies.platform(libs.redwood.bom))
    implementation(libs.redwood.layout.schema)
    implementation(libs.redwood.lazylayout.schema)
}

redwoodSchema {
    type = "com.santimattius.kmp.redwood.example.Schema"
}