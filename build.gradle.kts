plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.composeCompiler) apply false
}

buildscript{
    dependencies{
        classpath(libs.redwood.schema.gradle.plugin)
        classpath(libs.redwood.generator.compose)
        classpath(libs.redwood.generator.widget)
    }
}