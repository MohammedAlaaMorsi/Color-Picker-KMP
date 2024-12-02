
plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)

}


kotlin {
    jvmToolchain(11)
    androidTarget {

    }

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.1"
        summary = "Compose application framework"
        homepage = "empty"
        ios.deploymentTarget = "11.0"
        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.components.resources)
        }


        androidMain.dependencies {
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activityCompose)
            implementation(libs.compose.uitooling)
        }


        iosMain.dependencies {
        }


    }


}

android {
    namespace = "io.github.mohammedalaamorsi.colorpicker"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
}
