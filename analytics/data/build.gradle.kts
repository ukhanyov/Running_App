plugins {
    alias(libs.plugins.runningapp.android.library)
    alias(libs.plugins.runningapp.android.room)
}

android {
    namespace = "com.ukhanyov.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}