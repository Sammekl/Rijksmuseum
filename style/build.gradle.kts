plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.KOTLIN)

    implementation(Libraries.Coil.COIL)
    implementation(Libraries.Coil.BASE)

    implementation(Libraries.SHIMMER)

    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.ACTIVITY)
    implementation(Libraries.AndroidX.COMPAT)
    implementation(Libraries.AndroidX.MATERIAL)
    implementation(Libraries.AndroidX.CONSTRAINT_LAYOUT)
}
