plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)

}


kotlin {
    // JVM and Android
    jvmToolchain(11)
    androidTarget()
    jvm()
    // iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // macOS
    macosArm64()

    // watchOS
    watchosX64()

    // tvOS
    tvosArm64()

    js {
        browser()
        nodejs()
    }

    cocoapods {
        version = "1.0.2"
        summary = "Compose application framework"
        homepage = "empty"
        ios.deploymentTarget = "11.0"
        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Move Compose dependencies out of commonMain
                // since not all platforms support it
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
    namespace = "io.github.mohammedalaamorsi.colorpicker"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}
