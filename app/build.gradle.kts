plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

}

android {
    namespace = "net.taraabar.challengecode"
    compileSdk = 35

    defaultConfig {
        applicationId = "net.taraabar.challengecode"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        debug {
            buildConfigField("String", "TaraabarService", "\"TaraabarService\"")
            buildConfigField(
                "String",
                "TaraabarServiceURL",
                "\"https://taraabar-test.net/\""
            )
            resValue(
                "string",
                "app_name",
                "\"Taraabar_AriyaDarvishi_Debug_V${project.android.defaultConfig.versionName}\""
            )

        }
        release {

            buildConfigField("String", "TaraabarService", "\"TaraabarService\"")
            buildConfigField(
                "String",
                "TaraabarServiceURL",
                "\"https://taraabar.net/\""
            )
            isMinifyEnabled = false
            resValue(
                "string",
                "app_name",
                "\"Taraabar_AriyaDarvishi_Release_V${project.android.defaultConfig.versionName}\""
            )
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }

    sourceSets.configureEach {
        kotlin.srcDir(layout.buildDirectory.dir("generated/ksp/$name/kotlin").get().asFile)
    }
    ksp {
        arg("KSP_INCREMENTAL", "false")
        arg("KSP_LOG_LEVEL", "DEBUG")
    }
}

dependencies {

    implementation(project(":tools:designSystem"))
    implementation(project(":tools:network"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //shimmer
    implementation(libs.compose.shimmer)

    //navigation
    implementation(libs.compose.navigation)

    //di
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.okHttp.logger)

    implementation(libs.moshi)

    //coroutines
    implementation(libs.coroutines)


}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
            listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${layout.buildDirectory.get().asFile.absolutePath}/compose_metrics",
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${layout.buildDirectory.get().asFile.absolutePath}/compose_metrics"
            )
        )
    }
}