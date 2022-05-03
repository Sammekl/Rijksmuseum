buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.GRADLE_TOOLS}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.SAFE_ARGS}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}

apply(from = "${rootDir}/android-module.gradle")

subprojects {

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs =
                freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi" +
                        "-Xopt-in=kotlin.time.ExperimentalTime" +
                        "-Xopt-in=kotlinx.coroutines.FlowPreview"
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
