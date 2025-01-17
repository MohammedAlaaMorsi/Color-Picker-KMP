import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
}

kotlin {
    jvmToolchain(11)
    androidTarget ()
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        watchosX64(),
        tvosArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    // JavaScript
    js {
        browser()
        nodejs()
    }
    applyDefaultHierarchyTemplate()

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Move Compose dependencies out of commonMain
                // since not all platforms support it
                implementation(project(":colorpicker"))
                implementation(compose.runtime)
            }
        }

        // Create a new sourceset for compose-supporting platforms
        val composeMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
            }
        }

        val androidMain by getting {
            dependsOn(composeMain)
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(compose.ui)
            }
        }

        val iosMain by getting {
            dependsOn(composeMain)
            dependencies {
                implementation(compose.ui)
            }
        }

        val jvmMain by getting {
            dependsOn(composeMain)
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.ui)
            }
        }

        val jsMain by getting {
            dependsOn(composeMain)
            dependencies {
                implementation(compose.html.core)
            }
        }

    }
}

android {
    namespace = "io.github.mohammedalaamorsi.app"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35

        applicationId = "io.github.mohammedalaamorsi.app.androidApp"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageVersion = "1.0.2"
            packageName = "ColorPickerCMP.sample.composeApp"
        }
    }
}
