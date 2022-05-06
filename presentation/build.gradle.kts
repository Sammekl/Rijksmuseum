plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.STYLE))

    implementation(Libraries.KOTLIN)
    implementation(Libraries.KotlinCoroutines.CORE)

    implementation(Libraries.Coil.COIL)
    implementation(Libraries.Coil.BASE)

    implementation(Libraries.SHIMMER)

    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)
    implementation(Libraries.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Libraries.AndroidX.PAGING)
    implementation(Libraries.AndroidX.Navigation.FRAGMENT)
    implementation(Libraries.AndroidX.FRAGMENT)
    implementation(Libraries.AndroidX.Navigation.UI)
    implementation(Libraries.AndroidX.Lifecycle.LIVEDATA)
    implementation(Libraries.AndroidX.Lifecycle.VIEWMODEL)
    implementation(Libraries.AndroidX.Lifecycle.FRAGMENT)

    implementation(Libraries.Hilt.CORE)
    kapt(Libraries.Hilt.COMPILER)
    kapt(Libraries.Hilt.AndroidX.COMPILER)

    testImplementation(Libraries.JUNIT)
    testImplementation(Libraries.MOCKK)
    testImplementation(Libraries.TEST_CORE)
    testImplementation(Libraries.COROUTINES_TEST)
    testImplementation(project(Modules.COMMON_TEST))
}
