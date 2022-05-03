plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(Modules.DOMAIN))

    implementation(Libraries.KotlinCoroutines.CORE)

    implementation(Libraries.HTTP_LOGGING)


    implementation(Libraries.Retrofit.RETROFIT)
    implementation(Libraries.Retrofit.MOSHI)
    implementation(Libraries.MOSHI)

    implementation(Libraries.Coil.COIL)
    implementation(Libraries.Coil.BASE)

    implementation(Libraries.Hilt.CORE)
    implementation(Libraries.Hilt.AndroidX.WORK)
    kapt(Libraries.Hilt.COMPILER)
    kapt(Libraries.Hilt.AndroidX.COMPILER)

    implementation(Libraries.AndroidX.Lifecycle.LIVEDATA)

    debugImplementation(Libraries.Chucker.CHUCKER)
    releaseImplementation(Libraries.Chucker.NO_OP)

    testImplementation(Libraries.JUNIT)
    testImplementation(Libraries.MOCKK)
    testImplementation(Libraries.TEST_CORE)
    testImplementation(Libraries.COROUTINES_TEST)
}
