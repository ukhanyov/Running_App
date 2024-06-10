plugins {
    alias(libs.plugins.runningapp.android.library)
    alias(libs.plugins.runningapp.jvm.ktor)
}

android {
    namespace = "com.ukhanyov.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}