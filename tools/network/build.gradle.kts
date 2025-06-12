plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.compose)


}

android {
    namespace = "net.taraabar.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            consumerProguardFiles ("proguard-rules.pro")
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions{
        kotlinCompilerExtensionVersion ="1.5.15"
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

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))

    //coroutines
    implementation(libs.coroutines)


    //retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.okHttp.logger)

    //moshi
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)


    //di
    implementation(libs.hilt)
//    ksp(libs.hilt.compiler)
    kapt(libs.hilt.compiler)
}