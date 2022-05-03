plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

dependencies {
    implementation(Libraries.Hilt.CORE)
    kapt(Libraries.Hilt.COMPILER)
    kapt(Libraries.Hilt.AndroidX.COMPILER)

    implementation(Libraries.OK_HTTP)

    implementation(Libraries.KotlinCoroutines.CORE)
}
