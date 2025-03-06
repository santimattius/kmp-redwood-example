plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeCompiler)
}

dependencies {

    api(projects.schema.widget)
    implementation(project.dependencies.platform(libs.redwood.bom))
    implementation(libs.redwood.widget.compose)
    implementation(libs.redwood.composeui)

    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.jetbrains.compose.ui.tooling.preview)
}