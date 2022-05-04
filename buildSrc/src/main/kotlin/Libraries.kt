object Libraries {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_STD_LIB}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.OK_HTTP}"
    const val HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.HTTP_LOGGING}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER}"

    object AndroidX {

        const val CORE = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val ACTIVITY = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
        const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
        const val COMPAT = "androidx.appcompat:appcompat:${Versions.COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
        const val START_UP = "androidx.startup:startup-runtime:${Versions.ANDROIDX_START_UP}"

        object Navigation {
            const val FRAGMENT =
                "androidx.navigation:navigation-fragment:${Versions.ANDROIDX_NAVIGATION}"
            const val UI = "androidx.navigation:navigation-ui:${Versions.ANDROIDX_NAVIGATION}"
        }

        object Lifecycle {
            const val EXTENSIONS =
                "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSIONS}"
            const val LIVEDATA =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
            const val VIEWMODEL =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
            const val FRAGMENT =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        }
    }

    object KotlinCoroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    }

    object Hilt {
        const val CORE = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        object AndroidX {
            const val COMPILER = "androidx.hilt:hilt-compiler:${Versions.ANDROIDX_HILT}"
            const val WORK = "androidx.hilt:hilt-work:${Versions.ANDROIDX_HILT}"
        }

        object Test {
            const val TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
        }
    }

    object Coil {
        const val COIL = "io.coil-kt:coil:${Versions.COIL}"
        const val BASE = "io.coil-kt:coil-base:${Versions.COIL}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.MOSHI_RETROFIT}"
    }

    const val JUNIT = "org.junit.jupiter:junit-jupiter:${Versions.JUNIT}"
    const val JUNIT_EXTENSIONS = "io.github.glytching:junit-extensions:${Versions.JUNIT_EXTENSIONS}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versions.MOCKK}"
    const val TEST_CORE = "androidx.test:core:${Versions.TEST_CORE}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINE_TEST}"
    const val JUNIT_KTX = "androidx.test.ext:junit-ktx:${Versions.JUNIT_KTX}"

    object Chucker {
        const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"
        const val NO_OP = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"
    }
}
