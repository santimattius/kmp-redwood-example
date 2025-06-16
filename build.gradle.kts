plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinSerializationPlugin) apply false
}

buildscript{
    dependencies{
        classpath(libs.redwood.gradle.plugin)
        classpath(libs.redwood.schema.gradle.plugin)
        classpath(libs.redwood.generator.compose)
        classpath(libs.redwood.generator.widget)
        classpath(libs.redwood.generator.modifiers)
        classpath(libs.redwood.generator.protocol.guest)
        classpath(libs.redwood.generator.protocol.host)
        classpath(libs.zipline.plugin)
    }
}