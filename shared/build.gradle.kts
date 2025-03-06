plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.redwood.bom))
            implementation(libs.redwood.layout.uiview)

            implementation(projects.schema.widget)
            implementation(projects.presenter)
        }
    }
}
