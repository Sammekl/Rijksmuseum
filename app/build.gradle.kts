plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.TARGET_SDK
    buildToolsVersion = Versions.BUILD_TOOLS

    defaultConfig {
        applicationId = AppConfig.APP_ID
        minSdk = AppConfig.MINIMUM_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            applicationIdSuffix = ".${BuildTypes.DEBUG}"
        }
        getByName(BuildTypes.RELEASE) {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            matchingFallbacks.add(BuildTypes.RELEASE)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.PRESENTATION))

    implementation(Libraries.Retrofit.RETROFIT)
    implementation(Libraries.Retrofit.MOSHI)
    implementation(Libraries.MOSHI)

    implementation(Libraries.KOTLIN)
    implementation(Libraries.HTTP_LOGGING)
    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)
    implementation(Libraries.AndroidX.WORK)

    implementation(Libraries.Hilt.CORE)
    kapt(Libraries.Hilt.COMPILER)
    kapt(Libraries.Hilt.AndroidX.COMPILER)

    testImplementation(Libraries.JUNIT)
    testImplementation(Libraries.MOCKK)
    testImplementation(Libraries.TEST_CORE)
    testImplementation(Libraries.COROUTINES_TEST)
}
