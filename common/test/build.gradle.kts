plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(Libraries.AndroidX.Lifecycle.LIVEDATA)
    implementation(Libraries.AndroidX.CORE)

    implementation(Libraries.COROUTINES_TEST)
    implementation(Libraries.MOCKK)
    implementation(Libraries.TEST_CORE)
    implementation(Libraries.JUNIT)
}

tasks.withType<Test>().configureEach {
    enabled = false
}
