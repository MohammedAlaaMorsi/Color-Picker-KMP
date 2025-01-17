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
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)
            }
        }


        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(compose.ui)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.ui)
            }
        }
        val macosMain by getting {
            dependencies {
                implementation(compose.ui)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.ui)
            }
        }
        val jsMain by getting {
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
